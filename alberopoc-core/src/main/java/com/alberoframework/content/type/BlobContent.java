package com.alberoframework.content.type;

import com.alberoframework.store.blob.contract.BlobId;


public class BlobContent {
	protected BlobId blobId;
    protected String contentType;
    
	public BlobContent(BlobId blobId, String contentType) {
		this.blobId = blobId;
		this.contentType = contentType;
	}

	BlobContent() {
	}

	public BlobId getBlobId() {
		return blobId;
	}

	public String getContentType() {
		return contentType;
	}

}
