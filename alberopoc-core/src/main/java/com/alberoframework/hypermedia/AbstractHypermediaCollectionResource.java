package com.alberoframework.hypermedia;

import java.util.ArrayList;
import java.util.Map;

import com.google.common.collect.ImmutableList;

public abstract class AbstractHypermediaCollectionResource<T, R extends AbstractHypermediaCollectionResource<T, R>> extends AbstractHypermediaResource<T, R> {

	private final Iterable<T> elements;

	public AbstractHypermediaCollectionResource(Iterable<T> elements) {
		this.elements = ImmutableList.copyOf(elements);
	}
	
	protected AbstractHypermediaCollectionResource(Map<String, HypermediaLink> links, Map<String, Object> data, Map<String, Object> fieldPermissions, Object display,
			Iterable<T> elements) {
		super(links, data, fieldPermissions, display);
		this.elements = ImmutableList.copyOf(elements);
	}

	public AbstractHypermediaCollectionResource() {
		this(new ArrayList<>());
	}
	
	public Iterable<T> getElements() {
		return elements;
	}
	
//	@Override
//	protected HypermediaCollectionResource<T, R> copy(Map<String, HypermediaLink> links, Map<String, Object> data, Object display) {
//		return new HypermediaCollectionResource<T, R>(links, data, display, getElements());
//	}

}
