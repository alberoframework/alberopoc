package com.alberoframework.component.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.alberoframework.component.common.CommonTestStubs.EntityForRequestTestStub;
import com.alberoframework.component.common.CommonTestStubs.EntityForRequestTestStub2;
import com.alberoframework.component.common.CommonTestStubs.EntityForRequestTestStub2Repository;
import com.alberoframework.component.common.CommonTestStubs.EntityForRequestTestStubRepository;
import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.query.handler.AbstractSimpleAuthenticatedQueryHandler;
import com.alberoframework.component.query.handler.AbstractSimpleQueryHandler;
import com.alberoframework.lang.object.BaseObject;
import com.alberoframework.type.conversion.gateway.TypeConversionGateway;

public class QueryHandlerPocTestStubs {

	public static class ElementForQueryTestStub extends BaseObject {
		 private Long id;
		 private String property1;
		 
		public ElementForQueryTestStub(Long id, String property1) {
			this.id = id;
			this.property1 = property1;
		}

		public Long getId() {
			return id;
		}
	
		public void setId(Long id) {
			this.id = id;
		}
	
		public String getProperty1() {
			return property1;
		}
	
		public void setProperty1(String property1) {
			this.property1 = property1;
		}
		
	}
	
	public static class GetElementListQuery extends BaseObject implements Query<List<ElementForQueryTestStub>> {
		 	String property1;
		 	
			public GetElementListQuery(String property1) {
				this.property1 = property1;
			}

			public String getProperty1() {
				return property1;
			}
		
			public void setProperty1(String property1) {
				this.property1 = property1;
			}
		 
	}
	
	public static class GetElementListQueryHandler extends AbstractSimpleQueryHandler<GetElementListQuery, List<ElementForQueryTestStub>> {

		private EntityForRequestTestStubRepository entityForRequestTestStubRepository;

		@Override
		public List<ElementForQueryTestStub> doHandle(GetElementListQuery query, ContextualizedQueryGateway queryGateway) {
			List<ElementForQueryTestStub> result = new ArrayList<QueryHandlerPocTestStubs.ElementForQueryTestStub>();
			for (EntityForRequestTestStub entity : entityForRequestTestStubRepository.findByProperty1(query.getProperty1())) {
				result.add(new ElementForQueryTestStub(entity.getId(), entity.getProperty1()));
			}
			return result;
		}
		
		public void setEntityForRequestTestStubRepository(
				EntityForRequestTestStubRepository entityForRequestTestStubRepository) {
			this.entityForRequestTestStubRepository = entityForRequestTestStubRepository;
		}
		
	}
	
	
	
	public static class GetElementCountByPropertyQuery extends BaseObject implements Query<Integer> {
	 	String property1;
	 	
		public GetElementCountByPropertyQuery(String property1) {
			this.property1 = property1;
		}

		public String getProperty1() {
			return property1;
		}
	
		public void setProperty1(String property1) {
			this.property1 = property1;
		}
	 
}

	public static class GetElementCountByPropertyQueryHandler extends AbstractSimpleQueryHandler<GetElementCountByPropertyQuery, Integer> {
	
		private EntityForRequestTestStubRepository entityForRequestTestStubRepository;
		
		private EntityForRequestTestStub2Repository entityForRequestTestStub2Repository;
	
		@SuppressWarnings("unused")
		@Override
		public Integer doHandle(GetElementCountByPropertyQuery query, ContextualizedQueryGateway queryGateway) {
			Integer count = 0;
			for (EntityForRequestTestStub entity : entityForRequestTestStubRepository.findByProperty1(query.getProperty1())) {
				count++;
			}
			for (EntityForRequestTestStub2 entity : entityForRequestTestStub2Repository.findByProperty1(query.getProperty1())) {
				count++;
			}
			return count;
			
		}
		
		public void setEntityForRequestTestStub2Repository(
				EntityForRequestTestStub2Repository entityForRequestTestStub2Repository) {
			this.entityForRequestTestStub2Repository = entityForRequestTestStub2Repository;
		}
		
		public void setEntityForRequestTestStubRepository(
				EntityForRequestTestStubRepository entityForRequestTestStubRepository) {
			this.entityForRequestTestStubRepository = entityForRequestTestStubRepository;
		}
		
	}
	
	public static class GetElementCountByPropertyAuthenticatedQueryHandler extends AbstractSimpleAuthenticatedQueryHandler<GetElementCountByPropertyQuery, Integer> {
		
		private EntityForRequestTestStubRepository entityForRequestTestStubRepository;
		
		private EntityForRequestTestStub2Repository entityForRequestTestStub2Repository;
	
		@SuppressWarnings("unused")
		@Override
		public Integer doHandle(GetElementCountByPropertyQuery query, ContextualizedQueryGateway queryGateway) {
			Integer count = 0;
			for (EntityForRequestTestStub entity : entityForRequestTestStubRepository.findByProperty1(query.getProperty1())) {
				count++;
			}
			for (EntityForRequestTestStub2 entity : entityForRequestTestStub2Repository.findByProperty1(query.getProperty1())) {
				count++;
			}
			return count;
			
		}
		
		public void setEntityForRequestTestStub2Repository(
				EntityForRequestTestStub2Repository entityForRequestTestStub2Repository) {
			this.entityForRequestTestStub2Repository = entityForRequestTestStub2Repository;
		}
		
		public void setEntityForRequestTestStubRepository(
				EntityForRequestTestStubRepository entityForRequestTestStubRepository) {
			this.entityForRequestTestStubRepository = entityForRequestTestStubRepository;
		}
		
	}
	
	
	public static class GetDataFromOtherContextsQuery extends BaseObject implements Query<List<String>> {
	 	String property1;
	 	
		public GetDataFromOtherContextsQuery(String property1) {
			this.property1 = property1;
		}

		public String getProperty1() {
			return property1;
		}
	
		public void setProperty1(String property1) {
			this.property1 = property1;
		}
	}
	
	public static class GetSearchResultsFromExternalContext extends BaseObject implements Query<List<String>> {
	 	String search;
	 	
		public GetSearchResultsFromExternalContext(String search) {
			this.search = search;
		}

		public String getSearch() {
			return search;
		}
	}
	
	public static class GetBestMatchFromExternalContext extends BaseObject implements Query<Optional<String>> {
	 	String search;
	 	
		public GetBestMatchFromExternalContext(String search) {
			this.search = search;
		}

		public String getSearch() {
			return search;
		}
	}

	public static class GetDataFromOtherContextsQueryHandler extends AbstractSimpleQueryHandler<GetDataFromOtherContextsQuery, List<String>> {

		@Override
		public List<String> doHandle(GetDataFromOtherContextsQuery query, ContextualizedQueryGateway queryGateway) {
			List<String> result = new ArrayList<String>();
			result.addAll(queryGateway.handle(new GetSearchResultsFromExternalContext(query.getProperty1())));
			
			Optional<String> response2 = queryGateway.handle(new GetBestMatchFromExternalContext(query.getProperty1()));
			if (response2.isPresent())
				result.add(response2.get());
			return result;
		}
	
	}
	
	public static class GetDataFromOtherContextsAuthenticatedQueryHandler extends AbstractSimpleAuthenticatedQueryHandler<GetDataFromOtherContextsQuery, List<String>> {

		private TypeConversionGateway typeConversionGateway;
		
		@Override
		public List<String> doHandle(GetDataFromOtherContextsQuery query, ContextualizedQueryGateway queryGateway) {
			List<String> result = new ArrayList<String>();
			result.addAll(queryGateway.handle(new GetSearchResultsFromExternalContext(query.getProperty1())));
			
			Optional<String> response2 = queryGateway.handle(new GetBestMatchFromExternalContext(query.getProperty1()));
			if (response2.isPresent())
				result.add(response2.get());
			
			if (!result.isEmpty()) {
				Integer anInteger = typeConversionGateway.convert(query.getProperty1(), Integer.class);
				result.add(anInteger.toString());
			}
			return result;
		}
		
		public void setTypeConversionGateway(TypeConversionGateway typeConversionGateway) {
			this.typeConversionGateway = typeConversionGateway;
		}
	
	}
	
}
