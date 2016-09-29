package com.alberoframework.component.url;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.request.contract.Request;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.Optional;

public class SpringRequestUrlMapper implements RequestUrlMapper {

    private static final String GET = "GET";
    private static final String POST = "POST";

    private static final Logger logger = LoggerFactory.getLogger(SpringRequestUrlMapper.class);

    private final ObjectMapper mapper;
    private final String domain;
    private final DiskFileItemFactory factory;

    private final Map<String, SpringRequestMappingRegistry> apis = ImmutableMap.of(
        GET, SpringRequestMappingRegistry.create(),
        POST, SpringRequestMappingRegistry.create()
    );

    public SpringRequestUrlMapper(ObjectMapper mapper, String domain) {
        this.mapper = mapper;
        this.domain = domain;
        try {
            this.factory = new DiskFileItemFactory(10240, Files.createTempDirectory("MessageUrlMapper").toFile());
        }
        catch (IOException e) {
            throw new RuntimeException("Error creating temporary folder", e);
        }
    }

    public String resolveUrl(Request<?> request) {
        final SpringRequestMappingRegistry apiRegistry = apis.getOrDefault(resolveHttpMethod(request), SpringRequestMappingRegistry.empty());

        final Optional<SpringRequestMapping<?>> api = apiRegistry.findByRequest(request);

        if (!api.isPresent()) {
            throw new RuntimeException("Can't find a registered API for: " + request);
        }
        return api.get().link(request);
    }

    public Request<?> resolveRequest(HttpServletRequest request) throws IOException {
        logger.debug("[{}] {}", request.getMethod(), request.getRequestURI());

        final SpringRequestMappingRegistry apiRegistry = apis.getOrDefault(request.getMethod(), SpringRequestMappingRegistry.empty());

        final Optional<SpringRequestMapping<?>> api = apiRegistry.findByUri(request.getRequestURI());

        if (!api.isPresent()) {
            throw new IllegalArgumentException(String.format("No api mapping found for uri %s %s", request.getMethod(), request.getRequestURI()));
        }

        return api.get().createRequest(request);
    }

    public <C extends Query<?>> void registerQuery(String template, Class<C> type) {
        apis.get(GET).add(new SpringGetRequestMapping<>(domain, template, type));
    }

    public <C extends Command<?>> void registerCommand(String template, Class<C> type) {
        apis.get(POST).add(new SpringPostRequestMapping<>(domain, template, type, factory, mapper));
    }

    private static String resolveHttpMethod(Request<?> req) {
        return req instanceof Query
            ? GET
            : POST;
    }

    @Override
    public String toString() {
        return "SpringRequestUrlMapper{" +
            "domain='" + domain + '\'' +
            ", apis=" + apis +
            '}';
    }
}
