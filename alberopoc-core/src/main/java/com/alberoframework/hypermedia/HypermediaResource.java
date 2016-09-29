package com.alberoframework.hypermedia;

import java.util.Map;

import com.alberoframework.component.request.contract.Request;

public interface HypermediaResource<T, R extends HypermediaResource<T, R>> {

	public Map<String, HypermediaLink> getLinks();
    
    public Map<String, Object> getData();
    
    public Object getDisplay();
    
    public R withLink(HypermediaLink link);
    
    public R withLink(String rel, Request<?> request);
    
    public R withLinkTemplate(String rel, Request<?> request);
    
    public R withLink(boolean expression, String rel, Request<?> request);
    
    public R withLinkRemoved(String rel);
    
    public R withData(String key, Object data);
    
    public R withData(boolean expression, String key, Object data);
    
    public R withFieldPermission(String fieldName, Object fieldPermission);
    
    public R withFieldPermission(boolean expression, String fieldName, Object fieldPermission);
    
    public R withDisplay(Object display);
	
}
