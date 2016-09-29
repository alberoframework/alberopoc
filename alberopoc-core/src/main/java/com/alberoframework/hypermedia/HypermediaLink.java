package com.alberoframework.hypermedia;

import com.alberoframework.component.request.contract.Request;
import com.alberoframework.lang.object.BaseObject;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class HypermediaLink extends BaseObject {
	
	private String rel; //link name
	private String url;
	private boolean template = false;
	
	@JsonIgnore
	private Request<?> request;
	
	public HypermediaLink(String rel, String url, Request<?> request) {
		this.rel = rel;
		this.url = url;
		this.request = request;
	}
	
	public HypermediaLink(String rel, String url, Request<?> request, boolean template) {
		this.rel = rel;
		this.url = url;
		this.request = request;
		this.template = template;
	}
	
	public HypermediaLink(String rel, Request<?> request) {
		this.rel = rel;
		this.request = request;
	}
	
	public HypermediaLink(String rel, Request<?> request, boolean template) {
		this.rel = rel;
		this.request = request;
		this.template = template;
	}
	
	public HypermediaLink() {
	}
	
	public boolean isTemplate() {
		return template;
	}

	public String getRel() {
		return rel;
	}
	
	public String getUrl() {
		return url;
	}
	
	public Request<?> getRequest() {
		return request;
	}
	
	public void setRel(String rel) {
		this.rel = rel;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setRequest(Request<?> request) {
		this.request = request;
	}
	

	
	
}
