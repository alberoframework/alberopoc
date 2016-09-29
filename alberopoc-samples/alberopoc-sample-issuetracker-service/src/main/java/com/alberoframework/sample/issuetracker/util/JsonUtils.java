package com.alberoframework.sample.issuetracker.util;
import com.alberoframework.lang.object.BaseBeanUtils;
import com.alberoframework.sample.issuetracker.service.core.command.CreateIssueCategoryCommand;
import com.alberoframework.sample.issuetracker.service.core.command.CreateIssueCommand;
import com.alberoframework.sample.issuetracker.service.core.command.CreateProjectCommand;
import com.alberoframework.sample.issuetracker.service.core.command.CreateUserCommand;
import com.alberoframework.sample.issuetracker.service.core.value.MembershipValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();
    static {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public static void main(String[] args) {

        write(CreateProjectCommand.class);
        write(MembershipValue.class);
        write(CreateUserCommand.class);
        write(CreateIssueCategoryCommand.class);
        write(CreateIssueCommand.class);
    }

    private static void write(Class<?> type) {
        try {
            System.out.println(type.getName() + ":");
            System.out.println(mapper.writeValueAsString(BaseBeanUtils.newInstance(type)));
        } catch (JsonProcessingException e) {
            System.out.println("Error writing: " + type.getName());
            e.printStackTrace();
        }
    }
}
