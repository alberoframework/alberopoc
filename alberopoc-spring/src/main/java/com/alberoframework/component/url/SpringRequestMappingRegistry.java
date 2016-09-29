package com.alberoframework.component.url;

import com.alberoframework.component.request.contract.Request;
import com.alberoframework.lang.object.BaseObject;
import com.google.common.collect.ImmutableSet;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import static java.util.Comparator.comparingInt;

class SpringRequestMappingRegistry extends BaseObject{

    private static final SpringRequestMappingRegistry EMPTY = new SpringRequestMappingRegistry(ImmutableSet.of());

    public static SpringRequestMappingRegistry create() {
        return new SpringRequestMappingRegistry(new HashSet<>());
    }

    public static SpringRequestMappingRegistry empty() {
        return EMPTY;
    }

    private final Set<SpringRequestMapping<? extends Request<?>>> apis;

    private SpringRequestMappingRegistry(Set<SpringRequestMapping<? extends Request<?>>> apis) {
        this.apis = apis;
    }

    public void add(SpringRequestMapping<? extends Request<?>> api) {
        this.apis.add(api);
    }

    public Optional<SpringRequestMapping<?>> findByUri(String uri) {
        return apis.stream()
            .sorted()
            .filter(api -> api.matches(uri))
            .findFirst();
    }

    public Optional<SpringRequestMapping<? extends Request<?>>> findByRequest(Request<?> request) {
        return apis.stream()
            .filter(p -> p.type.isInstance(request))
            .findFirst();
    }
}
