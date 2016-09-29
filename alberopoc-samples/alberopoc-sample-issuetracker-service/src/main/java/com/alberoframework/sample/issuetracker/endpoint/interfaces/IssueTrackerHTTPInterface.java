package com.alberoframework.sample.issuetracker.endpoint.interfaces;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.command.gateway.SimpleAuthenticatedCommandGateway;
import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.gateway.SimpleAuthenticatedQueryGateway;
import com.alberoframework.component.request.contract.Request;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;
import com.alberoframework.component.url.RequestUrlMapper;
import com.alberoframework.content.constant.ContentTypeConstants;
import com.alberoframework.content.conversion.gateway.ContentConversionGateway;
import com.alberoframework.content.type.BinaryContent;
import com.alberoframework.core.validation.Validation;
import com.alberoframework.lang.VoidUnit;


@Controller
public class IssueTrackerHTTPInterface {

	private RequestUrlMapper requestUrlMapper;
	private SimpleAuthenticatedQueryGateway queryGateway;
	private SimpleAuthenticatedCommandGateway commandGateway;
	private ContentConversionGateway contentConversionGateway;
	private Map<Class<?>, String> defaultContentTypeResponseMap;
	
    public IssueTrackerHTTPInterface(RequestUrlMapper requestUrlMapper, SimpleAuthenticatedQueryGateway queryGateway,
									 SimpleAuthenticatedCommandGateway commandGateway, ContentConversionGateway contentConversionGateway) {
		this.requestUrlMapper = requestUrlMapper;
		this.queryGateway = queryGateway;
		this.commandGateway = commandGateway;
		this.contentConversionGateway = contentConversionGateway;
		this.defaultContentTypeResponseMap = new HashMap<>();
	}
    
    

	public IssueTrackerHTTPInterface(RequestUrlMapper requestUrlMapper, SimpleAuthenticatedQueryGateway queryGateway,
									 SimpleAuthenticatedCommandGateway commandGateway, ContentConversionGateway contentConversionGateway,
									 Map<Class<?>, String> defaultContentTypeResponseMap) {
		this(requestUrlMapper, queryGateway, commandGateway, contentConversionGateway);
		Validation.validate(defaultContentTypeResponseMap != null, IllegalArgumentException::new, "Default content type map cannot be null");
		this.defaultContentTypeResponseMap = defaultContentTypeResponseMap;
	}



	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login/login";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "redirect:/api";
    }

    @SuppressWarnings("unchecked")
	@RequestMapping(value="/api/**", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> get(HttpServletRequest httpRequest) throws Exception {
        final Request<?> query = requestUrlMapper.resolveRequest(httpRequest);

        SimpleAuthenticatedRequestEnvelope<Query<Object>, Object> env = new SimpleAuthenticatedRequestEnvelope<Query<Object>, Object>(httpRequest.getRemoteUser(), (Query<Object>) query);

        Object response = queryGateway.handle(env);
        
        if (response instanceof Optional) {
        	Optional<Object> responseOpt = (Optional<Object>) response;
        	if (!responseOpt.isPresent()) {
            	return new ResponseEntity<VoidUnit>(HttpStatus.NOT_FOUND);
            }
        	response = responseOpt.get();
        }

        return responseEntityFor(query, response);
    }

    @SuppressWarnings("unchecked")
	@RequestMapping(value="/api/**", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> post(HttpServletRequest httpRequest) throws Exception {
    	
    	final Request<?> command = requestUrlMapper.resolveRequest(httpRequest);

        SimpleAuthenticatedRequestEnvelope<Command<Object>, Object> env = new SimpleAuthenticatedRequestEnvelope<Command<Object>, Object>(httpRequest.getRemoteUser(), (Command<Object>) command);

        Object response = commandGateway.handle(env);
        
        if (response instanceof Optional) {
        	Optional<Object> responseOpt = (Optional<Object>) response;
        	if (!responseOpt.isPresent()) {
            	return new ResponseEntity<VoidUnit>(HttpStatus.OK); //NOT FOUND?
            }
        	response = responseOpt.get();
        }
        
        return responseEntityFor(command, response);
    }
    
    
    private ResponseEntity<?> responseEntityFor(Request<?> request, Object response) {
    	if (response instanceof BinaryContent) {
        	BinaryContent binaryContent = (BinaryContent) response;
	        return	ResponseEntity.ok()
		        	.contentType(MediaType.parseMediaType(binaryContent.getContentType()))
		        	.body(new InputStreamResource(new ByteArrayInputStream(binaryContent.getData())));
        }
        
        String responseContentType = ContentTypeConstants.JSON;
        
        if (defaultContentTypeResponseMap.containsKey(request.getClass())) {
        	responseContentType = defaultContentTypeResponseMap.get(request.getClass());
        }

        return ResponseEntity.ok()
        		.contentType(MediaType.parseMediaType(responseContentType))
        		.body(contentConversionGateway.convertToContent(responseContentType, response));
    }

    
}
