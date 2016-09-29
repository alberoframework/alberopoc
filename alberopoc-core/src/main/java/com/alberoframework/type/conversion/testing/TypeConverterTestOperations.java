package com.alberoframework.type.conversion.testing;

import com.alberoframework.testing.bdd.testcase.query.AbstractQueryTestOperation;
import com.alberoframework.type.conversion.converter.TypeConverter;

public class TypeConverterTestOperations {

	public static class TypeConverterTestOperation<C extends TypeConverter<S, T>, S, T> extends AbstractQueryTestOperation<TypeConverterTestContext<C, S, T>, T> {
		
		private S source;
		
		public TypeConverterTestOperation(S source) {
			this.source = source;
		}

		@Override
		protected T doExecute(TypeConverterTestContext<C, S, T> context) {
			C typeConverter = context.typeConverter();
			return typeConverter.convert(source);
		}
		
	}
	
}
