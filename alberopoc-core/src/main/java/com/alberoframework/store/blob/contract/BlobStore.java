package com.alberoframework.store.blob.contract;

import java.util.Optional;

public interface BlobStore {

	public void store(BlobId blobId, byte[] content);
	
	public Optional<byte[]> retrieve(BlobId blobId);
	
	public void copy(BlobId from, BlobId to);
	
}
