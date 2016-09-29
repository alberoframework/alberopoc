package com.alberoframework.sample.issuetracker.content.conversion.converter.json;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.alberoframework.core.string.Strings;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class IssueTrackerJacksonObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = 3959517174859782427L;
	
	public IssueTrackerJacksonObjectMapper() {
        setSerializationInclusion(Include.NON_NULL);

        super.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        super.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        super.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        super.setVisibility(PropertyAccessor.ALL, Visibility.ANY);

        registerModule(new JavaTimeModule());
//        registerModule(new JSR310Module());

        SimpleModule module = new SimpleModule();
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        registerModule(module);

    }
	
	private static class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
        @Override
        public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException{
            final String value = jp.getValueAsString();
            if (value.length() > 10) {
                return LocalDate.parse(value.substring(0, 10), DateTimeFormatter.ISO_LOCAL_DATE);
            }
            else {
                return LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE);
            }
        }
    }

	
    private static final DateTimeFormatter OFFSET_DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssx");
    
    private static class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException{
            final String value = jp.getValueAsString();
            if (value.isEmpty()) {
                return null;
            }
            else if (Strings.containsOnlyDigits(value)) {
            	return LocalDateTime.ofInstant(new Date(new Long(value)).toInstant(), ZoneId.systemDefault());
            }
            else if (value.length() == 24) {
                return LocalDateTime.parse(value, OFFSET_DATE_TIME);
            }
            else {
                return LocalDateTime.parse(value, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            }
        }
    }

}
