package com.alberoframework.component.url;

import org.springframework.web.util.UriTemplate;

import com.alberoframework.component.request.contract.Request;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

abstract class SpringRequestMapping<R extends Request<?>> implements Comparable<SpringRequestMapping<? extends Request<?>>>{

    public final Class<R> type;
    private final String domain;
    private final UriTemplate template;

    protected SpringRequestMapping(String domain, String template, Class<R> type) {
        this.domain = domain;
        this.template = new UriTemplate(template);
        this.type = type;
    }

    @Override
    public int compareTo(SpringRequestMapping<? extends Request<?>> o) {
        int res = Integer.compare(template.getVariableNames().size(), o.template.getVariableNames().size());
        if (res == 0) {
            res = Integer.compare(o.template.toString().split("/").length, template.toString().split("/").length);
        }
        return res;
    }

    public final boolean matches(String uri) {
        return this.template.matches(uri);
    }

    protected final Map<String, String> extractPathParams(String uri) {
        return this.template.match(uri);
    }

    protected final List<String> pathParamsNames() {
        return this.template.getVariableNames();
    }

    protected final String uri(Map<?, ?> params) {
        final String baseUri = domain + template.expand((Map)params).toString();
        return baseUri;
    }

    abstract String link(Object api);

    abstract R createRequest(HttpServletRequest request);

    @Override
    public boolean equals(Object o) {
        System.out.println("this = " + this);
        System.out.println("o = " + o);
        System.out.println("---------");
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpringRequestMapping api = (SpringRequestMapping) o;

        if (!domain.equals(api.domain)) return false;
        if (!template.equals(api.template)) return false;
        if (!type.equals(api.type)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + domain.hashCode();
        result = 31 * result + template.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Api{" +
            "type=" + type +
            ", domain='" + domain + '\'' +
            ", template=" + template +
            '}';
    }
}
