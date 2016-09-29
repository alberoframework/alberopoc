package com.alberoframework.core.iterable;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Iterables {

	public static <T> Stream<T> stream(Iterable<T> iterable) {
		return StreamSupport.stream(iterable.spliterator(), false);
	}
	
	public static <T> Stream<T> parallelStream(Iterable<T> iterable) {
		return StreamSupport.stream(iterable.spliterator(), true);
	}
	
}
