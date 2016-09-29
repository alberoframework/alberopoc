package com.alberoframework.hypermedia;

import java.util.ArrayList;
import java.util.Map;

public class HypermediaObjectCollectionResource<T> extends AbstractHypermediaCollectionResource<HypermediaObjectResource<T>, HypermediaObjectCollectionResource<T>> {

//	public static <K, V> HypermediaObjectCollectionResource<K> fromMap(Map<K, V> map) {
//		Iterable<HypermediaObjectResource<K>> elements = 
//			map.entrySet()
//			   .stream()
//			   .map(e -> new HypermediaObjectResource<K>(e.getKey(), e.getValue()))
//			   .collect(Collectors.toList());
//		
//		return new HypermediaObjectCollectionResource<K>(elements);
//	}
	
	public static <T> HypermediaObjectCollectionResource<T> empty() {
		return new HypermediaObjectCollectionResource<T>(new ArrayList<HypermediaObjectResource<T>>());
	}
	
	public HypermediaObjectCollectionResource(Iterable<HypermediaObjectResource<T>> elements) {
		super(elements);
	}
	
	public HypermediaObjectCollectionResource(Map<String, HypermediaLink> links, Map<String, Object> data,
			Map<String, Object> fieldPermissions, Object display, Iterable<HypermediaObjectResource<T>> elements) {
		super(links, data, fieldPermissions, display, elements);
	}
	
	public HypermediaObjectCollectionResource<T> withElements(Iterable<HypermediaObjectResource<T>> elements) {
		return new HypermediaObjectCollectionResource<T>(getLinks(), getData(), getFieldPermissions(), getDisplay(), elements);
	}

	@Override
	protected HypermediaObjectCollectionResource<T> copy(Map<String, HypermediaLink> links, Map<String, Object> data,
			Map<String, Object> fieldPermissions, Object display) {
		return new HypermediaObjectCollectionResource<T>(links, data, fieldPermissions, display, getElements());
	}

	
}
