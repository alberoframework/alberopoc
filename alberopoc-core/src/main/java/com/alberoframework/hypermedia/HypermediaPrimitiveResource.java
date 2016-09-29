package com.alberoframework.hypermedia;

import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Splitter;

public class HypermediaPrimitiveResource<T> extends AbstractHypermediaResource<T, HypermediaPrimitiveResource<T>> {

	public static <E extends Enum<E>> HypermediaPrimitiveResource<String> fromEnum(E enumValue) {
		String enumDisplay = Splitter.on(" ")
									.splitToList(enumValue.name().toLowerCase().replace("_", " "))
							        .stream()
							        .map(StringUtils::capitalize)
							        .collect(Collectors.joining(" "));
		
		return new HypermediaPrimitiveResource<String>(enumValue.name(), enumDisplay);
	}
	
	private T value;

	public HypermediaPrimitiveResource(T value) {
		this.value = value;
	}
	
	public HypermediaPrimitiveResource(T value, Object display) {
		super(display);
		this.value = value;
	}
	
	protected HypermediaPrimitiveResource(Map<String, HypermediaLink> links, Map<String, Object> data, Map<String, Object> fieldPermissions, Object display, T value) {
		super(links, data, fieldPermissions, display);
		this.value = value;
	}

	public T getValue() {
		return value;
	}
	
	@Override
	protected HypermediaPrimitiveResource<T> copy(Map<String, HypermediaLink> links, Map<String, Object> data,
			Map<String, Object> fieldPermissions, Object display) {
		return new HypermediaPrimitiveResource<T>(links, data, fieldPermissions, display, getValue());
	}
	
	
}
