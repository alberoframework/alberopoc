package com.alberoframework.component.url;

import com.alberoframework.component.request.contract.Request;
import com.alberoframework.lang.object.BaseBeanUtils;
import com.google.common.collect.ImmutableSet;

import org.apache.commons.beanutils.BeanMap;
import org.springframework.web.util.UriUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Set;

class SpringGetRequestMapping<T extends Request<?>> extends SpringRequestMapping<T>{

    SpringGetRequestMapping(String domain, String template, Class<T> type) {
        super(domain, template, type);
    }

    @Override
    public String link(Object api) {
        final Map<?, ?> map = new BeanMap(api);
        final String baseUri = uri(map);
        return baseUri + queryParams(map);
    }

    @Override
    public T createRequest(HttpServletRequest request) {
        final Map<String, String[]> params = request.getParameterMap();
        final Map<String, String> pathParams = extractPathParams(request.getRequestURI());
        final T instance = BaseBeanUtils.newInstance(this.type);
        return BaseBeanUtils.populate(BaseBeanUtils.populate(instance, pathParams), params);
    }

    private String queryParams(Map<?, ?> beanMap) {
        final Set<String> ignore = ImmutableSet.<String>builder()
            .add("id")
            .add("class")
            .addAll(pathParamsNames())
            .build();

        final StringBuilder uri = new StringBuilder("?");

        beanMap.forEach((name, value) -> {
            if (value != null && !ignore.contains(name)) {
                try {
                    uri.append(name)
                        .append("=")
                        .append(UriUtils.encodeQueryParam(value.toString(), "UTF-8"))
                        .append("&");
                } catch (UnsupportedEncodingException e1) {
                    throw new RuntimeException(e1);
                }
            }
        });

        return uri.substring(0, uri.length() - 1).toString();
    }
}
