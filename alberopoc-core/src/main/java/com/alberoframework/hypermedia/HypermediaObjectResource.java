package com.alberoframework.hypermedia;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class HypermediaObjectResource<T> extends AbstractHypermediaResource<T, HypermediaObjectResource<T>> {

	@JsonUnwrapped
	private T value;

	public HypermediaObjectResource(T value) {
		this.value = value;
	}
	
	public HypermediaObjectResource(T value, Object display) {
		super(display);
		this.value = value;
	}
	
	protected HypermediaObjectResource(Map<String, HypermediaLink> links, Map<String, Object> data, Map<String, Object> fieldPermissions, Object display, T value) {
		super(links, data, fieldPermissions, display);
		this.value = value;
	}

	public T getValue() {
		return value;
	}
	
	@Override
	protected HypermediaObjectResource<T> copy(Map<String, HypermediaLink> links, Map<String, Object> data,
			Map<String, Object> fieldPermissions, Object display) {
		return new HypermediaObjectResource<T>(links, data, fieldPermissions, display, getValue());
	}
	
	
}
