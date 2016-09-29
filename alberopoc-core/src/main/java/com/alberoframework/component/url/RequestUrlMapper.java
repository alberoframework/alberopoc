package com.alberoframework.component.url;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.request.contract.Request;

public interface RequestUrlMapper {

    public String resolveUrl(Request<?> request);
    
    public Request<?> resolveRequest(HttpServletRequest request) throws IOException;

    public <C extends Query<?>> void registerQuery(String template, Class<C> type);

    public <C extends Command<?>> void registerCommand(String template, Class<C> type);

}
