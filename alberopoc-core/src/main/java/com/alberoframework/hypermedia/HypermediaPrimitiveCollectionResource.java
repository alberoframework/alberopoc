package com.alberoframework.hypermedia;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class HypermediaPrimitiveCollectionResource<T> extends AbstractHypermediaCollectionResource<HypermediaPrimitiveResource<T>, HypermediaPrimitiveCollectionResource<T>> {

	public static <E extends Enum<E>> HypermediaPrimitiveCollectionResource<String> fromEnumValues(E[] enumValues) {
		
		Iterable<HypermediaPrimitiveResource<String>> elements = 
				Arrays.asList(enumValues)
					  .stream()
					  .map(HypermediaPrimitiveResource::fromEnum)
					  .sorted((e1, e2) -> e1.getDisplay().toString().compareTo(e2.getDisplay().toString()))
					  .collect(Collectors.toList());
		
		return new HypermediaPrimitiveCollectionResource<String>(elements);
	}
	
	public static <K, V> HypermediaPrimitiveCollectionResource<K> fromMap(Map<K, V> map) {
		Iterable<HypermediaPrimitiveResource<K>> elements = 
			map.entrySet()
			   .stream()
			   .map(e -> new HypermediaPrimitiveResource<K>(e.getKey(), e.getValue()))
			   .sorted((e1, e2) -> e1.getDisplay().toString().compareTo(e2.getDisplay().toString()))
			   .collect(Collectors.toList());
		
		return new HypermediaPrimitiveCollectionResource<K>(elements);
	}
	
	public HypermediaPrimitiveCollectionResource(Iterable<HypermediaPrimitiveResource<T>> elements) {
		super(elements);
	}

	public HypermediaPrimitiveCollectionResource(Map<String, HypermediaLink> links, Map<String, Object> data,
			Map<String, Object> fieldPermissions, Object display, Iterable<HypermediaPrimitiveResource<T>> elements) {
		super(links, data, fieldPermissions, display, elements);
	}
	
	public HypermediaPrimitiveCollectionResource<T> withElements(Iterable<HypermediaPrimitiveResource<T>> elements) {
		return new HypermediaPrimitiveCollectionResource<T>(getLinks(), getData(), getFieldPermissions(), getDisplay(), elements);
	}

	@Override
	protected HypermediaPrimitiveCollectionResource<T> copy(Map<String, HypermediaLink> links, Map<String, Object> data,
			Map<String, Object> fieldPermissions, Object display) {
		return new HypermediaPrimitiveCollectionResource<T>(links, data, fieldPermissions, display, getElements());
	}
}
