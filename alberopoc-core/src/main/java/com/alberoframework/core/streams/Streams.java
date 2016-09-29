package com.alberoframework.core.streams;

import java.util.function.BinaryOperator;
import java.util.function.Supplier;

public class Streams {

	public static <T> BinaryOperator<T> reduceToAtMostOneElementOrFail() {
		return reduceToAtMostOneElementOrFail(() -> new IllegalStateException("Duplicate Elements Found"));
	}
	
	public static <T, E extends RuntimeException> BinaryOperator<T> reduceToAtMostOneElementOrFail(Supplier<E> exception) {
		return (e1, e2) -> {
			throw exception.get();
		};
	}
	
}
