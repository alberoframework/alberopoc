package com.alberoframework.content.template.pdf;

import java.io.OutputStream;

public interface PdfTemplate<T> {

	public void bind(T model, OutputStream outputStream);
	
}
