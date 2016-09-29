package com.alberoframework.component.query;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.alberoframework.component.query.QueryHandlerPocTestStubs.GetBestMatchFromExternalContext;
import com.alberoframework.component.query.QueryHandlerPocTestStubs.GetDataFromOtherContextsQuery;
import com.alberoframework.component.query.QueryHandlerPocTestStubs.GetDataFromOtherContextsQueryHandler;
import com.alberoframework.component.query.QueryHandlerPocTestStubs.GetSearchResultsFromExternalContext;
import com.alberoframework.component.query.testing.SimpleQueryHandlerStatelessTestSupport;
import com.google.common.collect.Lists;

public class GetDataFromOtherContextsQueryHandlerTest extends SimpleQueryHandlerStatelessTestSupport<GetDataFromOtherContextsQueryHandler, GetDataFromOtherContextsQuery, List<String>>{

	@SuppressWarnings("unchecked")
	@Test
	public TestSpecification testSuccess() {
		
		return 	given(
				 		queryStubs(
			 				queryStub(query(new GetSearchResultsFromExternalContext("search1")), Lists.newArrayList("e1", "e2")),
			 				queryStub(query(new GetBestMatchFromExternalContext("search1")), nonEmpty("e3")),
			 				queryStub(query(new GetSearchResultsFromExternalContext("search2")), new ArrayList<String>()),
			 				queryStub(query(new GetBestMatchFromExternalContext("search2")), empty()),
			 				queryStub(query(new GetSearchResultsFromExternalContext("search3")), Lists.newArrayList("e7", "e4")),
			 				queryStub(query(new GetBestMatchFromExternalContext("search3")), nonEmpty("e11"))
				 		)
					 )
					.when(handle(query(new GetDataFromOtherContextsQuery("search1"))))
					.then(
							Lists.newArrayList("e1", "e2", "e3"),
							queriesSent(
								query(new GetSearchResultsFromExternalContext("search1")),
								query(new GetBestMatchFromExternalContext("search1"))
							)
					 )
					.when(handle(query(new GetDataFromOtherContextsQuery("search2"))))
					.then(new ArrayList<String>())
					.when(handle(query(new GetDataFromOtherContextsQuery("search3"))))
					.then(
							Lists.newArrayList("e7", "e4", "e11"),
							queriesSent(
								query(new GetSearchResultsFromExternalContext("search1")),
								query(new GetBestMatchFromExternalContext("search1")),
								query(new GetSearchResultsFromExternalContext("search2")),
								query(new GetBestMatchFromExternalContext("search2")),
								query(new GetSearchResultsFromExternalContext("search3")),
								query(new GetBestMatchFromExternalContext("search3"))
							)
						);
 								
	}
	
}
