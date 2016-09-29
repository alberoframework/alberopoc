package com.alberoframework.lang.functional;

import java.util.Optional;
import java.util.function.Supplier;

public class OptionalUtils {

    @SafeVarargs
	public static <T> Optional<T> or(Supplier<Optional<T>>...suppliers) {
        for (Supplier<Optional<T>> s : suppliers) {
            final Optional<T> res = s.get();
            if (res.isPresent()) {
                return res;
            }
        }
        return Optional.empty();
    };
}

