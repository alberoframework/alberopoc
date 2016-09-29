package com.alberoframework.domain.repository.persistence.csv;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.alberoframework.core.validation.Validation;
import com.alberoframework.domain.entity.contract.Entity;
import com.alberoframework.domain.repository.persistence.memory.AbstractInMemoryReadRepository;
import com.google.common.base.Splitter;

public abstract class AbstractCsvReadRepository<E extends Entity<ID>, ID> extends AbstractInMemoryReadRepository<E, ID> {
	
	private static String NEWLINE_CHARACTER = "\n";
	private static String COMMENT_CHARACTER = "#";
	private static String SEPARATION_CHARACTER = ";";
	
	public AbstractCsvReadRepository(String csvPath) {
		InputStream is = getClass().getResourceAsStream(csvPath);
		Validation.validate(is != null, IllegalStateException::new, "CSV was not found in path: " + csvPath);
		String csvContent;
        try {
        	csvContent = IOUtils.toString(is);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        Splitter.on(NEWLINE_CHARACTER).splitToList(csvContent)
        				 .stream()
        				 .filter(line -> !line.trim().isEmpty())
        				 .filter(line -> !line.startsWith(COMMENT_CHARACTER))
        				 .map(line -> Splitter.on(SEPARATION_CHARACTER).splitToList(line))
        				 .map(splitLine -> entityFromCsvLine(splitLine))
        				 .forEach(e -> persist(e));
	}
	
	protected abstract E entityFromCsvLine(List<String> csvLine);

}
