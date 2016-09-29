package com.alberoframework.hypermedia;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

public class HypermediaCollectors {
	
	public static <T> Collector<HypermediaObjectResource<T>, List<HypermediaObjectResource<T>>, HypermediaObjectCollectionResource<T>> toObjectCollection() {
        return Collector.of(ArrayList::new, List::add, (left, right) -> {
            left.addAll(right);
            return left;
        }, HypermediaObjectCollectionResource<T>::new);
    }
	
	public static <T> Collector<HypermediaPrimitiveResource<T>, List<HypermediaPrimitiveResource<T>>, HypermediaPrimitiveCollectionResource<T>> toPrimitiveCollection() {
        return Collector.of(ArrayList::new, List::add, (left, right) -> {
            left.addAll(right);
            return left;
        }, HypermediaPrimitiveCollectionResource<T>::new);
    }
	
}
