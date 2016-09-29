package com.alberoframework.store.blob.contract;

import com.alberoframework.lang.object.BaseObject;
import com.alberoframework.store.PartitionId;

public class BlobId extends BaseObject {
   
    private PartitionId partition;
    
    private String name;

	public BlobId(PartitionId partition, String name) {
		this.partition = partition;
		this.name = name;
	}

	BlobId() {
	}

	public PartitionId getPartition() {
		return partition;
	}

	public void setPartition(PartitionId partition) {
		this.partition = partition;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
