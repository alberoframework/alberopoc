package com.alberoframework.core.validation;

import java.util.function.Function;
import java.util.function.Supplier;

public class Validation {

	public static void validate(boolean expression, Supplier<? extends RuntimeException> exceptionType) {
        if (!expression) {
            throw exceptionType.get();
        }
    }

    public static void validate(boolean expression, Function<String, ? extends RuntimeException> exceptionType, String message) {
        if (!expression) {
            throw exceptionType.apply(message);
        }
    }


    public static void validate(boolean expression, Function<String, ? extends RuntimeException> exceptionType, String errorMessageTemplate, Object... errorMessageArgs) {
    	validate(expression, exceptionType, format(errorMessageTemplate, errorMessageArgs));
    }


    //TODO: Replace this piece of code (copied from google guava) with a simpler one

    // Note that this is somewhat-improperly used from Verify.java as well.
    static String format(String template, Object... args) {
        template = String.valueOf(template); // null -> "null"

        // start substituting the arguments into the '%s' placeholders
        StringBuilder builder = new StringBuilder(template.length() + 16 * args.length);
        int templateStart = 0;
        int i = 0;
        while (i < args.length) {
            int placeholderStart = template.indexOf("%s", templateStart);
            if (placeholderStart == -1) {
                break;
            }
            builder.append(template.substring(templateStart, placeholderStart));
            builder.append(args[i++]);
            templateStart = placeholderStart + 2;
        }
        builder.append(template.substring(templateStart));

        // if we run out of placeholders, append the extra args in square braces
        if (i < args.length) {
            builder.append(" [");
            builder.append(args[i++]);
            while (i < args.length) {
                builder.append(", ");
                builder.append(args[i++]);
            }
            builder.append(']');
        }

        return builder.toString();
    }


}
