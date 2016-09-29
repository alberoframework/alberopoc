package com.alberoframework.type.conversion.converter;

import org.junit.Test;

import com.alberoframework.type.conversion.TypeConversionStubs.GetStringForConversionQuery;
import com.alberoframework.type.conversion.TypeConversionStubs.IntegerToStringConverterWithQueryAndConversionRequests;
import com.alberoframework.type.conversion.testing.SimpleAuthenticatedTypeConverterTestSupport;

public class IntegerToStringConverterWithQueryAndConversionRequestsTest extends SimpleAuthenticatedTypeConverterTestSupport<IntegerToStringConverterWithQueryAndConversionRequests, Integer, String> {

	@SuppressWarnings("unchecked")
	@Test
	public TestSpecification testSuccessfulConversions() {
		return   given(
					portStubs(
						typeConversionStubs(
							typeConversionStub(typeConversionRequest(7, Long.class), 42L),
							typeConversionStub(typeConversionRequest(7, String.class), "TOSTRING"), //not used but useful to verify that the stubber doenst mix types
							typeConversionStub(typeConversionRequest(8, Long.class), 47L) 
						),
						queryStubs(
							queryStub(query(new GetStringForConversionQuery(7)), nonEmpty("CONVSTRING")),
							queryStub(query(new GetStringForConversionQuery(8)), empty())
						)
					)
				 )
				.when(convert(7))
				.then(
					"CONVSTRING49",
					portRequests(
						typeConversionRequestsSent(
							typeConversionRequest(7, Long.class)
						),
						queriesSent(
							query(new GetStringForConversionQuery(7))
						)
					)
				 )
				.when(convert(8))
				.then(
					"55",
					portRequests(
						typeConversionRequestsSent(
							typeConversionRequest(7, Long.class),
							typeConversionRequest(8, Long.class)
						),
						queriesSent(
							query(new GetStringForConversionQuery(7)),
							query(new GetStringForConversionQuery(8))
						)
					)
				);
	}
	
}
