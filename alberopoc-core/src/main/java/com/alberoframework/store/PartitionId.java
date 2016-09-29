package com.alberoframework.store;

import static java.util.stream.Collectors.joining;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.alberoframework.core.validation.Validation;
import com.alberoframework.lang.object.BaseObject;
import com.google.common.collect.ImmutableList;

public class PartitionId extends BaseObject {

    private static PartitionId ROOT = new PartitionId(ImmutableList.of());

    public static final PartitionId root() {
        return ROOT;
    }

    public static PartitionId from(String ... parts) {
    	Validation.validate(parts.length > 0, IllegalArgumentException::new, "At least one partition must be specified");
        return new PartitionId(Arrays.asList(parts));
    }
    
    private final List<String> parts;

    private PartitionId(List<String> parts) {
        this.parts = parts;
    }
    
    private PartitionId() {
    	this.parts = new ArrayList<>();
    }

    public PartitionId append(PartitionId other) {
    	Validation.validate(other != null, IllegalArgumentException::new, "PartitionId cannot be null");
    	List<String> newParts = new ArrayList<String>(this.parts);
    	newParts.addAll(other.parts);
        return new PartitionId(newParts);
    }

    public List<String> getParts() {
        return Collections.unmodifiableList(this.parts);
    }

    public String joinPartsBy(String join) {
        return this.parts.stream().collect(joining(join));
    }

    @Override
    public String toString() {
        return this.parts.stream()
            .collect(joining("/", "PartitionId[/", "]"));
    }
}

