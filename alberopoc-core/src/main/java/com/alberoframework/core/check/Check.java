package com.alberoframework.core.check;

import java.util.Collection;
import java.util.Objects;

public class Check {

	public static boolean isNull(Object o) {
        return o == null;
    }

    public static boolean notNull(Object o) {
        return !isNull(o);
    }

    public static boolean isBlank(String s) {
        return isNull(s) || s.isEmpty();
    }

    public static boolean notBlank(String s) {
        return !isBlank(s);
    }

    public static boolean equals(Object o1, Object o2) {
        return Objects.equals(o1, o2);
    }

    public static boolean notEquals(Object o1, Object o2) {
        return !equals(o1, o2);
    }

    public static boolean nonNegative(Integer i) {
        return notNull(i) && i >= 0;
    }

    public static boolean nonNegative(Double i) {
        return notNull(i) && i >= 0.0;
    }

    public static boolean positive(Integer i) {
        return notNull(i) && i > 0;
    }

    public static boolean isEmpty(Collection<?> c) {
        return notNull(c) && c.isEmpty();
    }

    public static boolean notEmpty(Collection<?> c) {
        return notNull(c) && !c.isEmpty();
    }
	
}
