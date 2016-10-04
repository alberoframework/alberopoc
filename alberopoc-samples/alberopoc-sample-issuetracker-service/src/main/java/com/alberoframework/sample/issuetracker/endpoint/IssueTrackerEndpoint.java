package com.alberoframework.sample.issuetracker.endpoint;

import java.nio.file.Paths;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.repository.support.Repositories;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;

import com.alberoframework.component.authorization.gateway.SimpleAuthorizationGateway;
import com.alberoframework.component.command.gateway.SimpleAuthenticatedCommandGateway;
import com.alberoframework.component.command.gateway.SpringSimpleAuthenticatedCommandGateway;
import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.query.gateway.SimpleAuthenticatedQueryGateway;
import com.alberoframework.component.query.gateway.SpringSimpleAuthenticatedQueryGateway;
import com.alberoframework.component.query.handler.AndSimpleAuthenticatedPredicateQueryHandler;
import com.alberoframework.component.query.handler.FalseSimpleAuthenticatedPredicateQueryHandler;
import com.alberoframework.component.query.handler.NotSimpleAuthenticatedPredicateQueryHandler;
import com.alberoframework.component.query.handler.OrSimpleAuthenticatedPredicateQueryHandler;
import com.alberoframework.component.query.handler.TrueSimpleAuthenticatedPredicateQueryHandler;
import com.alberoframework.component.query.handler.XorSimpleAuthenticatedPredicateQueryHandler;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;
import com.alberoframework.component.request.gateway.SpringRequestHandlerScanner;
import com.alberoframework.component.request.handler.SimpleAuthenticatedLoggerRequestHandlerWrapper;
import com.alberoframework.component.url.RequestUrlMapper;
import com.alberoframework.content.conversion.gateway.ContentConversionGateway;
import com.alberoframework.domain.command.NewSimpleIdentityEnricherCommandHandlerWrapper;
import com.alberoframework.hypermedia.HypermediaResponseConverterRequestHandlerWrapper;
import com.alberoframework.remote.http.interfaces.SpringHttpCORSFilter;
import com.alberoframework.sample.issuetracker.endpoint.authorization.IssueTrackerAuthorization;
import com.alberoframework.sample.issuetracker.endpoint.content.IssueTrackerContentConversion;
import com.alberoframework.sample.issuetracker.endpoint.content.IssueTrackerDefaultContentTypeMapping;
import com.alberoframework.sample.issuetracker.endpoint.interfaces.IssueTrackerHTTPInterface;
import com.alberoframework.sample.issuetracker.endpoint.security.IssueTrackerUserDetailService;
import com.alberoframework.sample.issuetracker.endpoint.url.IssueTrackerUrlMapping;
import com.alberoframework.service.infrastructure.date.CurrentDateTimeSimpleAuthenticatedQueryHandler;
import com.alberoframework.store.blob.contract.BlobStore;
import com.alberoframework.store.blob.persistence.filesystem.FileSystemBlobStore;
import com.google.common.collect.Lists;

@SpringBootApplication
@ComponentScan(basePackages = { "com.alberoframework.sample.issuetracker" }, excludeFilters = @Filter(value = Controller.class))
@EnableMongoRepositories(basePackages = { "com.alberoframework.sample.issuetracker" })
@EnableCaching
public class IssueTrackerEndpoint {

	public static void main(String[] args) {
		SpringApplication.run(IssueTrackerEndpoint.class, args);
	}

	@Bean
	public UserDetailsService userDetailService(Environment env, SimpleAuthenticatedQueryGateway queryGateway) {
		ContextualizedQueryGateway contextualizedQueryGateway = new ContextualizedQueryGateway() {
			@Override
			public <R> R handle(Query<R> query) {
				return queryGateway.handle(SimpleAuthenticatedRequestEnvelope
						.envelopeWithSystemUser(query));
			}
		};

		return new IssueTrackerUserDetailService(contextualizedQueryGateway,
			env.getProperty("authentication.admin.username"),
			env.getProperty("authentication.admin.password")
		);
	}

	@Bean
	public IssueTrackerHTTPInterface httpInterface(
			RequestUrlMapper requestUrlMapper,
			SimpleAuthenticatedQueryGateway queryGateway,
			SimpleAuthenticatedCommandGateway commandGateway,
			ContentConversionGateway contentConversionGateway
    ) {
		return new IssueTrackerHTTPInterface(
            requestUrlMapper,
            queryGateway,
            commandGateway,
            contentConversionGateway,
            IssueTrackerDefaultContentTypeMapping.defaultContentTypeResponseMap()
        );
	}

	@Bean
	public SpringHttpCORSFilter corsFilter() {
		return new SpringHttpCORSFilter();
	}

	@Bean
	public HypermediaResponseConverterRequestHandlerWrapper hypermediaRequestHandlerWrapper(
			RequestUrlMapper requestUrlMapper,
			SimpleAuthorizationGateway authorizationGateway
    ) {
		return new HypermediaResponseConverterRequestHandlerWrapper(requestUrlMapper, authorizationGateway);
	}

	@Bean
	public NewSimpleIdentityEnricherCommandHandlerWrapper identityEnricherHandlerWrapper() {
		return new NewSimpleIdentityEnricherCommandHandlerWrapper();
	}

	@Bean
	public SimpleAuthenticatedLoggerRequestHandlerWrapper loggerHandlerWrapper() {
		return new SimpleAuthenticatedLoggerRequestHandlerWrapper();
	}

	@SuppressWarnings("unchecked")
	@Bean
	public SimpleAuthenticatedQueryGateway queryGateway(ApplicationContext applicationContext) {
		return new SpringSimpleAuthenticatedQueryGateway(
            new SpringRequestHandlerScanner(applicationContext),
            Lists.newArrayList(HypermediaResponseConverterRequestHandlerWrapper.class)
        );
	}

	@SuppressWarnings("unchecked")
	@Bean
	public SimpleAuthenticatedCommandGateway commandGateway(ApplicationContext applicationContext) {
		return new SpringSimpleAuthenticatedCommandGateway(
            new SpringRequestHandlerScanner(applicationContext),
            Lists.newArrayList(
                NewSimpleIdentityEnricherCommandHandlerWrapper.class,
                HypermediaResponseConverterRequestHandlerWrapper.class,
                SimpleAuthenticatedLoggerRequestHandlerWrapper.class
            )
        );
	}

	@Bean
	public ContentConversionGateway contentConversionGateway() {
		return IssueTrackerContentConversion
				.createConversionGatewayWithConverters();
	}

	@Bean
	public RequestUrlMapper requestUrlMapper(Environment env) {
		return IssueTrackerUrlMapping.createRequestUrlMapperWithMappings(ObjectUtils.defaultIfNull(env.getProperty("baseurl"), ""));
	}

	@Bean
	public SimpleAuthorizationGateway authorizationGateway(SimpleAuthenticatedQueryGateway queryGateway) {
		return IssueTrackerAuthorization
				.createAuthorizationGatewayWithSpecifications(queryGateway);

	}

	@Bean
	public BlobStore blobStore(Environment env) {
		return new FileSystemBlobStore(Paths.get(env
				.getProperty("blobstore.filesystem.basepath")));
	}

	// IMPORT

	@Bean
	public Repositories repositories(ApplicationContext applicationContext) {
		return new Repositories(applicationContext);
	}

	// GENERIC HANDLERS
	@Bean
	public CurrentDateTimeSimpleAuthenticatedQueryHandler currentDateHandler() {
		return new CurrentDateTimeSimpleAuthenticatedQueryHandler();
	}

	@Bean
	public AndSimpleAuthenticatedPredicateQueryHandler andPredicateQueryHandler(
			SimpleAuthenticatedQueryGateway queryGateway) {
		return new AndSimpleAuthenticatedPredicateQueryHandler(queryGateway);
	}

	@Bean
	public OrSimpleAuthenticatedPredicateQueryHandler orPredicateQueryHandler(
			SimpleAuthenticatedQueryGateway queryGateway) {
		return new OrSimpleAuthenticatedPredicateQueryHandler(queryGateway);
	}

	@Bean
	public NotSimpleAuthenticatedPredicateQueryHandler notPredicateQueryHandler(
			SimpleAuthenticatedQueryGateway queryGateway) {
		return new NotSimpleAuthenticatedPredicateQueryHandler(queryGateway);
	}

	@Bean
	public XorSimpleAuthenticatedPredicateQueryHandler xorPredicateQueryHandler(
			SimpleAuthenticatedQueryGateway queryGateway) {
		return new XorSimpleAuthenticatedPredicateQueryHandler(queryGateway);
	}

	@Bean
	public TrueSimpleAuthenticatedPredicateQueryHandler truePredicateQueryHandler(
			SimpleAuthenticatedQueryGateway queryGateway) {
		return new TrueSimpleAuthenticatedPredicateQueryHandler(queryGateway);
	}

	@Bean
	public FalseSimpleAuthenticatedPredicateQueryHandler falsePredicateQueryHandler(
			SimpleAuthenticatedQueryGateway queryGateway) {
		return new FalseSimpleAuthenticatedPredicateQueryHandler(queryGateway);
	}
}
