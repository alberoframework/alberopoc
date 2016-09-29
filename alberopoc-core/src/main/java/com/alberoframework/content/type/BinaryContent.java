package com.alberoframework.content.type;

import org.apache.commons.io.FileUtils;

import com.alberoframework.lang.object.BaseObject;

public class BinaryContent extends BaseObject {

	protected byte[] data;
    protected String contentType;

    public BinaryContent(byte[] data, String contentType) {
		this.data = data;
		this.contentType = contentType;
	}

	BinaryContent() {
	}

	public byte[] getData() {
		return data;
	}

	public String getContentType() {
		return contentType;
	}

	@Override
    public String toString() {
        return "BinaryContent {" +
            "data=" + FileUtils.byteCountToDisplaySize(data.length) +
            ", contentType='" + contentType + '\'' +
            '}';
    }
    
}
