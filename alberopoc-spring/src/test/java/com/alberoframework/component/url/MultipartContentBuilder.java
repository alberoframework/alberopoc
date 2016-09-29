package com.alberoframework.component.url;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang3.RandomStringUtils;

public class MultipartContentBuilder {
    private static final String CRLF = "\r\n";

    public static MultipartContentBuilder multipart(){
        return multipart(RandomStringUtils.randomAlphabetic(5));
    }
    public static MultipartContentBuilder multipart(String boundary){
        return new MultipartContentBuilder(boundary);
    }

    private final StringBuilder raw;
    private final String boundary;

    public MultipartContentBuilder(String boundary) {
        this.boundary = boundary;
        this.raw = new StringBuilder("--").append(boundary).append(CRLF);
    }

    public MultipartContentBuilder withParam(String name, String value) {
        raw.append("Content-Disposition: form-data; name=\"").append(name).append("\";").append(CRLF)
            .append(CRLF)
            .append(value)
            .append(CRLF)
            .append("--").append(boundary)
            .append(CRLF);
        return this;
    }

    public MultipartContentBuilder withContent(String name, String filename, byte[] content, String contentType) {
        raw.append("Content-Disposition: form-data; name=\"").append(name).append("\"; filename=\"").append(filename).append("\"").append(CRLF)
            .append("Content-type: ").append(contentType).append(CRLF)
            .append(CRLF)
            .append(new String(content))
            .append(CRLF);
        return this;
    }

    public MultipartServletInputStream inputStream() {
        final ByteArrayInputStream is = new ByteArrayInputStream((raw + "--" + boundary + "--").getBytes());
        return new MultipartServletInputStream(is, boundary);
    }

    public static class MultipartServletInputStream extends InputStream{

        private final InputStream content;
        private final String boundary;

        private MultipartServletInputStream(InputStream content, String boundary) {
            this.content = content;
            this.boundary = boundary;
        }

        @Override
        public int read() throws IOException {
            return content.read();
        }

        @Override
        public int available() throws IOException {
            return content.available();
        }

        public String boundary() {
            return boundary;
        }

        public String contentType() {
            return new StringBuilder("multipart/form-data; boundary=").append(boundary).toString();
        }
    }
}

