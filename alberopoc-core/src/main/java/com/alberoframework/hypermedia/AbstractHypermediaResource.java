package com.alberoframework.hypermedia;

import java.util.HashMap;
import java.util.Map;

import com.alberoframework.component.request.contract.Request;
import com.alberoframework.lang.VoidUnit;
import com.alberoframework.lang.object.BaseObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableMap;

public abstract class AbstractHypermediaResource<T, R extends HypermediaResource<T, R>> extends BaseObject implements HypermediaResource<T, R> {
	
	@JsonProperty("_links")
	private final Map<String, HypermediaLink> links;

	@JsonProperty("_data")
    private final Map<String, Object> data;
	
	@JsonProperty("_fieldPermissions")
    private final Map<String, Object> fieldPermissions;
	
	@JsonProperty("_display")
    private final Object display;
	
	public AbstractHypermediaResource() {
		this(new HashMap<>(), new HashMap<>(), new HashMap<>(),VoidUnit.instance());
	}
	
	protected AbstractHypermediaResource(Object display) {
		this(new HashMap<>(), new HashMap<>(), new HashMap<>(), display);
	}
	
    protected AbstractHypermediaResource(Map<String, HypermediaLink> links, Map<String, Object> data, Map<String, Object> fieldPermissions, Object display) {
		this.links = ImmutableMap.copyOf(links);
		this.data = ImmutableMap.copyOf(data);
		this.fieldPermissions = ImmutableMap.copyOf(fieldPermissions);
		this.display = display;
	}

	public Map<String, HypermediaLink> getLinks() {
		return links;
	}
    
    public Map<String, Object> getData() {
		return data;
	}
    
    public Map<String, Object> getFieldPermissions() {
		return fieldPermissions;
	}
    
    public Object getDisplay() {
    	return display;
    }
    
    public R withData(boolean expression, String key, Object data) {
        return (expression) ? withData(key, data) : copy(getLinks(), getData(), getFieldPermissions(), getDisplay());
    }
    
    public R withData(String key, Object data) {
    	Map<String, Object> dataMap = new HashMap<>(getData());
    	dataMap.put(key, data);
        return copy(getLinks(), dataMap, getFieldPermissions(), getDisplay());
    }
    
    @Override
    public R withDisplay(Object display) {
    	return copy(getLinks(), getData(), getFieldPermissions(), display);
    }
    
    public R withLink(boolean expression, String rel, Request<?> request) {
        return (expression) ? withLink(new HypermediaLink(rel, request)) : copy(getLinks(), getData(), getFieldPermissions(), getDisplay());
    }
    
    public R withLink(String rel, Request<?> request) {
        return withLink(new HypermediaLink(rel, request));
    }
    
    public R withLinkTemplate(String rel, Request<?> request) {
        return withLink(new HypermediaLink(rel, request, true));
    }
    
    public R withLink(HypermediaLink link) {
    	Map<String, HypermediaLink> linksMap = new HashMap<>(getLinks());
        linksMap.put(link.getRel(), link);
        return copy(linksMap, getData(), getFieldPermissions(), getDisplay());
    }
    
    public R withLinkRemoved(String rel) {
    	Map<String, HypermediaLink> linksMap = new HashMap<>(getLinks());
        linksMap.remove(rel);
        return copy(linksMap, getData(), getFieldPermissions(), getDisplay());
    }
    
    public R withFieldPermission(String fieldName, Object fieldPermission) {
    	Map<String, Object> fieldPermissionMap = new HashMap<>(getFieldPermissions());
    	fieldPermissionMap.put(fieldName, fieldPermission);
        return copy(getLinks(), getData(), fieldPermissionMap, getDisplay());
    }
    
    @Override
    public R withFieldPermission(boolean expression, String fieldName, Object fieldPermission) {
    	return (expression) ? withFieldPermission(fieldName, fieldPermission) : copy(getLinks(), getData(), getFieldPermissions(), getDisplay());
    }
    
    protected abstract R copy(Map<String, HypermediaLink> links, Map<String, Object> data, Map<String, Object> fieldPermissions, Object display);
    
}
