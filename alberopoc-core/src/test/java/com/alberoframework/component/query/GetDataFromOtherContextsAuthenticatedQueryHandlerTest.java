package com.alberoframework.component.query;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.alberoframework.component.query.QueryHandlerPocTestStubs.GetBestMatchFromExternalContext;
import com.alberoframework.component.query.QueryHandlerPocTestStubs.GetDataFromOtherContextsAuthenticatedQueryHandler;
import com.alberoframework.component.query.QueryHandlerPocTestStubs.GetDataFromOtherContextsQuery;
import com.alberoframework.component.query.QueryHandlerPocTestStubs.GetSearchResultsFromExternalContext;
import com.alberoframework.component.query.testing.SimpleAuthenticatedQueryHandlerStatelessTestSupport;
import com.google.common.collect.Lists;

public class GetDataFromOtherContextsAuthenticatedQueryHandlerTest extends SimpleAuthenticatedQueryHandlerStatelessTestSupport<GetDataFromOtherContextsAuthenticatedQueryHandler, GetDataFromOtherContextsQuery, List<String>>{

	@SuppressWarnings("unchecked")
	@Test
	public TestSpecification testSuccess() {
		String userId = "userId";
		return 	given(
						portStubs(
							queryStubs(
				 				queryStub(query(new GetSearchResultsFromExternalContext("search1"), userId), Lists.newArrayList("e1", "e2")),
				 				queryStub(query(new GetBestMatchFromExternalContext("search1"), userId), nonEmpty("e3")),
				 				queryStub(query(new GetSearchResultsFromExternalContext("search2"), userId), new ArrayList<String>()),
				 				queryStub(query(new GetBestMatchFromExternalContext("search2"), userId), empty()),
				 				queryStub(query(new GetSearchResultsFromExternalContext("search3"), userId), Lists.newArrayList("e7", "e4")),
				 				queryStub(query(new GetBestMatchFromExternalContext("search3"), userId), nonEmpty("e11"))
						 	),
							typeConversionStubs(
								typeConversionStub(typeConversionRequest("search1", Integer.class), 77),
								typeConversionStub(typeConversionRequest("search2", Integer.class), 17),
								typeConversionStub(typeConversionRequest("search3", Integer.class), 42)
							)
						)
				 		
					 )
					.when(handle(query(new GetDataFromOtherContextsQuery("search1"), userId)))
					.then(
							Lists.newArrayList("e1", "e2", "e3", "77"),
							portRequests(
								queriesSent(
									query(new GetSearchResultsFromExternalContext("search1"), userId),
									query(new GetBestMatchFromExternalContext("search1"), userId)
								),
								typeConversionRequestsSent(
									typeConversionRequest("search1", Integer.class)
								)
							)
							
					 )
					.when(handle(query(new GetDataFromOtherContextsQuery("search2"), userId)))
					.then(new ArrayList<String>())
					.when(handle(query(new GetDataFromOtherContextsQuery("search3"), userId)))
					.then(
							Lists.newArrayList("e7", "e4", "e11", "42"),
							portRequests(
								queriesSent(
									query(new GetSearchResultsFromExternalContext("search1"), userId),
									query(new GetBestMatchFromExternalContext("search1"), userId),
									query(new GetSearchResultsFromExternalContext("search2"), userId),
									query(new GetBestMatchFromExternalContext("search2"), userId),
									query(new GetSearchResultsFromExternalContext("search3"), userId),
									query(new GetBestMatchFromExternalContext("search3"), userId)
								),
								typeConversionRequestsSent(
									typeConversionRequest("search1", Integer.class),
									typeConversionRequest("search3", Integer.class)
								)
							)
							
						);
 								
	}

}
