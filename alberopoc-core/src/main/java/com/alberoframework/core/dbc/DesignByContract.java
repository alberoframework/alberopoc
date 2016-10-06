package com.alberoframework.core.dbc;

import com.alberoframework.core.validation.Validation;

public class DesignByContract {

	public static void require(boolean expression) {
        Validation.validate(expression, PreconditionViolatedException::new);
    }

    public static void require(boolean expression, Object message) {
    	Validation.validate(expression, PreconditionViolatedException::new, String.valueOf(message));
    }

    public static void require(boolean expression, String errorMessageTemplate, Object... errorMessageArgs) {
    	Validation.validate(expression, PreconditionViolatedException::new, errorMessageTemplate, errorMessageArgs);
    }


    public static void ensure(boolean expression) {
    	Validation.validate(expression, PostconditionViolatedException::new);
    }

    public static void ensure (boolean expression, Object message) {
    	Validation.validate(expression, PostconditionViolatedException::new, String.valueOf(message));
    }

    public static void ensure (boolean expression, String errorMessageTemplate, Object... errorMessageArgs) {
    	Validation.validate(expression, PostconditionViolatedException::new, errorMessageTemplate, errorMessageArgs);
    }


	public static class PreconditionViolatedException extends IllegalArgumentException {
    	
		private static final long serialVersionUID = -8000139364736671535L;

		public PreconditionViolatedException() {
        }

        public PreconditionViolatedException(String s) {
            super(s);
        }

        public PreconditionViolatedException(String message, Throwable cause) {
            super(message, cause);
        }

        public PreconditionViolatedException(Throwable cause) {
            super(cause);
        }
    }

	public static class PostconditionViolatedException extends RuntimeException {

		private static final long serialVersionUID = 3894931889158828730L;

		public PostconditionViolatedException() {
        }

        public PostconditionViolatedException(String message) {
            super(message);
        }

        public PostconditionViolatedException(String message, Throwable cause) {
            super(message, cause);
        }

        public PostconditionViolatedException(Throwable cause) {
            super(cause);
        }
    }
	
}
