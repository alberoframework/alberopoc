package com.alberoframework.content.template.pdf.itextdsl;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Function;
import java.util.stream.Stream;

import org.apache.commons.io.IOUtils;

import com.alberoframework.content.template.pdf.PdfTemplate;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.List;
import com.lowagie.text.ListItem;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public abstract class AbstractItextPdfTemplate<T> implements PdfTemplate<T> {

    private static final Element IGNORE = new Chunk();

    protected final Font defaultFont;
    protected final Font boldFont;
    protected final Font italicFont;
    private final Font smallFont;
    protected Document document;
    protected AbstractItextPdfTemplate(BaseFont defaultFont, int size) {
        this.defaultFont = new Font(defaultFont, size, Font.NORMAL);
        this.boldFont = new Font(defaultFont, size, Font.BOLD);
        this.italicFont = new Font(defaultFont, size, Font.ITALIC);
        this.smallFont = new Font(defaultFont, size - 2);
    }

    public void bind(T model, OutputStream out) {
        this.document = new Document(PageSize.A4, 50, 50, 30, 30);
        try {
	        final PdfWriter writer = PdfWriter.getInstance(document, out);
	
	        document.setHeader(getHeader());
	        document.setFooter(getFooter());
	
	        document.open();
        
	        bind(model, writer.getDirectContent());
        }
        catch (Exception e) {
            throw new RuntimeException("Error elaborating pdf with model " + model, e);
        }
        finally {
        	if (document.isOpen())
        		document.close();
        }
    }

    public Document getDocument() {
        return document;
    }

    protected abstract void bind(T model, PdfContentByte directContent) throws DocumentException;
    protected abstract HeaderFooter getHeader();
    protected abstract HeaderFooter getFooter();

    public Chunk text(boolean condition, String text, Supplier<?>...values) {
        if (condition) {
            return text(text, Stream.of(values).map(s -> s.get()).toArray(size -> new Object[size]));
        }
        else {
            return new Chunk();
        }
    }
    public Phrase phrase(Object... chunks) {
        Phrase c = new Phrase();
        Stream.of(chunks).forEach(c::add);
        return c;
    }
    public Chunk text(String text, Object...values) {
        return text(format(text, values));
    }

    public Chunk text(String text) {
        if (text == null) return new Chunk();
        return text(text, style(
            cFont(defaultFont)
        ));
    }

    public Chunk small(String text, Object...values) {
        if (text == null) return new Chunk();
        return text(format(text, values), style(
            cFont(smallFont)
        ));
    }
    public Chunk bold(String text, Object...values) {
        return text(format(text, values), style(
            cFont(boldFont)
        ));
    }

    protected String format(String text, Object...values) {
        if (text == null) {
            return "";
        }
        return MessageFormat.format(text, Stream.of(values).map(o -> o == null ? "" : o).toArray());
    }

    public Chunk italic(String text, Object...values) {
        return text(format(text, values), style(
            cFont(italicFont)
        ));
    }
    public Chunk superscript(String text) {
        return text(text, style(
            cFont(smallFont),
            c -> c.setTextRise(5f)
        ));
    }
    public static Chunk emptyLine(){
        return Chunk.NEWLINE;
    }
    protected void add(Element e) throws DocumentException {
        document.add(e);
    }
    protected void add(Element...elements) throws DocumentException {
        add(emptyLine());
        for (Element e : elements) {
            add(e);
        }
    }
    protected Element conditional(boolean condition, Element e) {
        if (condition) {
            return e;
        }
        else {
            return IGNORE;
        }
    }
    protected Element conditional(boolean condition, Element...e) {
        if (condition) {
            return phrase((Object[])e);
        }
        else {
            return IGNORE;
        }
    }

    protected Paragraph paragraph(String text) {
        return paragraph(text(text));
    }
    /*
     * PARAGRAPH SECTION
     */
    private static Paragraph paragraph(Style<Paragraph> style) {
        final Paragraph paragraph = new Paragraph();
        paragraph.setLeading(0, 1);
        style.apply(paragraph);
        return paragraph;
    }
    protected static Paragraph paragraph(String text, Style<Paragraph> style){
        final Paragraph p = paragraph(style);
        p.add(text);
        return p;
    }
    protected static Paragraph paragraph(Element...elements) {
        return paragraph(AbstractItextPdfTemplate.<Paragraph>style(), elements);
    }
    protected static Paragraph paragraph(Style<Paragraph> style, Element...elements) {
        final Paragraph paragraph = paragraph(style);
        for (Element e : elements) {
            if (e != IGNORE) {
                paragraph.add(e);
                paragraph.add(emptyLine());
            }
        }
        return paragraph;
    }
    
    @SafeVarargs
    protected static <T extends Element> Style<T> style(ElementModifier<? super T>...modifiers) {
        return new Style<>(Arrays.asList(modifiers));
    }
    protected static ElementModifier<? super Paragraph> leading(final float px, final float relative) {
        return element -> element.setLeading(px, relative);
    }
    public enum Align {LEFT, CENTER, RIGHT}
    protected static ElementModifier<Paragraph> align(final Align align) {
        return element -> element.setAlignment(align.name());
    }
    protected static ElementModifier<Paragraph> align(final int align) {
        return element -> element.setAlignment(align);
    }
    protected static ElementModifier<Paragraph> indentationLeft(final float value) {
        return element -> element.setIndentationLeft(value);
    }
    protected static Paragraph centered(Paragraph paragraph) {
        paragraph.setAlignment(Element.ALIGN_CENTER);
        return paragraph;
    }

    public static ElementModifier<Paragraph> KeepTogether() {
        return element -> element.setKeepTogether(true);
    }
    protected static Paragraph lines(Element...elements) {
        final Paragraph paragraph = paragraph(AbstractItextPdfTemplate.<Paragraph>style());
        for (int i = 0 ; i < elements.length; i++) {
            paragraph.add(elements[i]);
            if (i < (elements.length - 1))
                paragraph.add(emptyLine());
        }
        return paragraph;
    }
    protected static ElementModifier<? super Paragraph> font(final Font font) {
        return element -> element.setFont(font);
    }
    /*
     * CHUNK SECTION
     */
    public static Chunk text(String text, Style<Chunk> style) {
        final Chunk chunk = new Chunk();
        style.apply(chunk);
        chunk.append(text);
        return chunk;
    }
    public static ElementModifier<Chunk> cFont(final Font font) {
        return element -> element.setFont(font);
    }
    /*
     * LIST SECTION
     */
    @SafeVarargs
	public final ListBuilder list(ElementModifier<List>...modifiers) {
        return new ListBuilder(style(modifiers));
    }
    public class ListBuilder {
        private final java.util.List<ListItem> elements = new LinkedList<>();
        private final Style<List> style;
        private Style<ListItem> itemStyle;

        public ListBuilder(Style style) {
            this.style = style;
            this.itemStyle = style(
                font(defaultFont),
                leading(0, 1)
            );
        }

        public ListBuilder items(java.util.List<String> items){
            return items(items, Function.identity());
        }
        public <E> ListBuilder items(java.util.List<E> items, Function<E, String> toString){
            items.stream().map(toString).forEach(this::item);
            return this;
        }

        private ListItem item(){
            final ListItem item = new ListItem();
            itemStyle.apply(item);
            return item;
        }

        public <P extends Phrase> ListBuilder item(P phrase) {
            ListItem item = item();
            item.add(phrase);
            elements.add(item);
            return this;
        }
        public ListBuilder item(Chunk phrase) {
            ListItem item = item();
            item.add(phrase);
            elements.add(item);
            return this;
        }
        public ListBuilder item(String text) {
            return item(paragraph(text(text)));
        }
        public ListBuilder item(String text, boolean conditional) {
            if (conditional) {
                return item(paragraph(text(text)));
            }
            else {
                return this;
            }
        }

        public List build() {
            final List list = new List();
            list.setIndentationLeft(20);
            this.style.apply(list);
            elements.forEach(list::add);
            return list;
        }
    }

    public interface ElementModifier<T extends Element> {
        void modify(T element);
    }
    public static final class Style<T extends Element> {
        private final java.util.List<ElementModifier<? super T>> modifiers;

        public Style() {
            this.modifiers = new ArrayList<>();
        }
        public Style(java.util.List<ElementModifier<? super T>> modifiers) {
            this.modifiers = modifiers;
        }
        public void apply(T element) {
            for (ElementModifier<? super T> modifier : modifiers) {
                modifier.modify(element);
            }
        }

        public Style<T> add(ElementModifier<? super T> modifier) {
            return new Style<>(ImmutableList.<ElementModifier<? super T>>builder().addAll(modifiers).add(modifier).build());
        }

        public Style<T> join(Style<T> style) {
            return new Style<>(ImmutableList.<ElementModifier<? super T>>builder().addAll(modifiers).addAll(style.modifiers).build());
        }
    }
    public static ElementModifier<List> symbol(final String symbol) {
        return list -> list.setListSymbol(symbol + "    ");
    }
    public static ElementModifier<List> ordered(){
        return list -> {
            list.setNumbered(true);
            list.setLettered(false);
        };
    }
    public static ElementModifier<List> lettered(){
        return list -> {
            list.setNumbered(false);
            list.setLettered(true);
            list.setLowercase(true);
        };
    }
    public static ElementModifier<List> indent(final int indent) {
        return element -> element.setIndentationLeft(indent);
    }
    public static ElementModifier<? super Paragraph> SpacingBefore(final float spacing) {
        return new ElementModifier<Paragraph>() {
            @Override
            public void modify(Paragraph element) {
                element.setSpacingBefore(spacing);
            }
        };
    }
    /*
     * TABLES
     */

    @SafeVarargs
	public final TableBuilder table(ElementModifier<PdfPTable>...modifiers) {
        return new TableBuilder(style(modifiers));
    }

    public class TableBuilder {
        private final Style<PdfPTable> tableStyle;
        private Style<PdfPCell> cellStyle = new Style<>();
        private Supplier<PdfPTable> factory;
        private final java.util.List<PdfPCell> cells = new ArrayList<>();

        public TableBuilder(Style<PdfPTable> tableStyle) {
            this.tableStyle = tableStyle;
        }

        public TableBuilder columns(final float...columnsSize) {
            this.factory = () -> new PdfPTable(columnsSize);
            return this;
        }
        public TableBuilder columns(final int number) {
            this.factory = () -> new PdfPTable(number);
            return this;
        }

        @SafeVarargs
		public final TableBuilder cellStyles(ElementModifier<PdfPCell>...modifiers) {
            this.cellStyle = style(modifiers);
            return this;
        }

        public TableBuilder cell(String element) {
            return cell(new Style(), text(element));
        }
        public TableBuilder cellBody(Collection<Element> elements) {
            final PdfPCell cell = new PdfPCell();
            cellStyle.apply(cell);
            elements.forEach(cell::addElement);
            cells.add(cell);
            return this;
        }
        public TableBuilder cell(Style<PdfPCell> style, Element element) {
            final PdfPCell cell = new PdfPCell();
            cellStyle.join(style).apply(cell);
            cell.addElement(element);
            cells.add(cell);
            return this;
        }
        public TableBuilder cell(Element element) {
            return cell(new Style<>(), element);
        }
        public PdfPTable build() {
            if (factory == null) {
                throw new IllegalStateException("columns number isn't set. Use the columns(Integer) or columns(Float[]) method.");
            }
            final PdfPTable table = factory.get();
            tableStyle.apply(table);
            cells.forEach(table::addCell);
            return table;
        }
    }

    public static ElementModifier<PdfPTable> width(final float percentage) {
        return element -> element.setWidthPercentage(percentage);
    }
    public static ElementModifier<PdfPTable> keepTogether() {
        return element -> element.setKeepTogether(true);
    }
    public static ElementModifier<PdfPTable> spacingBefore(final int px) {
        return element -> element.setSpacingBefore(px);
    }
    public static ElementModifier<PdfPTable> spacingAfter(final int px) {
        return element -> element.setSpacingAfter(px);
    }
    public static ElementModifier<PdfPCell> border(final int border) {
        return element -> element.setBorder(border);
    }
    public static ElementModifier<PdfPCell> border(final boolean border) {
        return element -> {
            if (border){
                element.enableBorderSide(PdfPCell.TOP);
                element.enableBorderSide(PdfPCell.BOTTOM);
                element.enableBorderSide(PdfPCell.LEFT);
                element.enableBorderSide(PdfPCell.RIGHT);
            }
            else{
                element.setBorder(PdfPCell.NO_BORDER);
            }
        };
    }
    public static ElementModifier<PdfPCell> height(final int size) {
        return cell -> cell.setFixedHeight(size);
    }
    public static ElementModifier<PdfPCell> fixedHeight(final float height) {
        return element -> element.setFixedHeight(height);
    }
    public static ElementModifier<PdfPCell> hAlign(final int align) {
        return element -> element.setHorizontalAlignment(align);
    }
    public static ElementModifier<PdfPCell> vAlign(final int align) {
        return element -> element.setVerticalAlignment(align);
    }
    public static ElementModifier<PdfPCell> background(final Color color) {
        return element -> element.setBackgroundColor(color);
    }

    
    
    public static class TextFactory {
        private final Font defaultFont;
        private final Font boldFont;

        public TextFactory(Font defaultFont) {
            this.defaultFont = defaultFont;
            this.boldFont = new Font(defaultFont);
            boldFont.setStyle(Font.BOLD);
        }

        public Font getDefaultFont() {
            return defaultFont;
        }

        public Font getBoldFont() {
            return boldFont;
        }

        public Phrase text(String content) {
            return new Phrase(content, defaultFont);
        }
        public Phrase bold(String content) {
            return new Phrase(content, boldFont);
        }
    }

    public PdfPCell rowspan(PdfPCell cell, int rowspan) {
        cell.setRowspan(rowspan);
        return cell;
    }

    public PdfPCell colspan(PdfPCell cell, int colspan) {
        cell.setColspan(colspan);
        return cell;
    }
    
    public Image image(String resource) {
        try {
            return Image.getInstance(load(resource));
        } catch (Exception e) {
            throw new RuntimeException("There was a problem with the resource " + resource, e);
        }
    }

    public byte[] load(String resource) {
        final InputStream stream = getClass().getResourceAsStream(resource);
        if (stream == null) {
            throw new IllegalArgumentException("Not found " + resource);
        }
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            IOUtils.copy(stream, out);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            IOUtils.closeQuietly(stream);
        }

        return out.toByteArray();
    }

}
