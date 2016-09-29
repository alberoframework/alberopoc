package com.alberoframework.sample.issuetracker.endpoint.security;

import java.util.Objects;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.sample.issuetracker.service.core.entity.UserEntity;
import com.alberoframework.sample.issuetracker.service.core.function.UserFunctions;
import com.alberoframework.sample.issuetracker.service.core.query.UserByUsernameQuery;
import com.alberoframework.sample.issuetracker.service.core.value.UserRoleValue;
import com.google.common.collect.ImmutableList;

public class IssueTrackerUserDetailService implements UserDetailsService {

    private final ContextualizedQueryGateway queryGateway;
    
    private final String adminUsername;
    private final String adminPassword;

    public IssueTrackerUserDetailService(ContextualizedQueryGateway queryGateway, String adminUsername, String adminPassword) {
        this.queryGateway = queryGateway;
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
    	if (Objects.equals(adminUsername, username)) {
    		return new User(adminUsername, UserFunctions.hashPassword(adminPassword), ImmutableList.of(new SimpleGrantedAuthority(UserRoleValue.ADMIN.toString())));
    	}
    		
    	UserEntity user = queryGateway.handle(new UserByUsernameQuery(username)).orElseThrow(() -> new UsernameNotFoundException(username + "doesn't exist"));
    	return new User(user.getUserId(), user.getPassword(), ImmutableList.of(new SimpleGrantedAuthority(user.getRole().name())));
    }
}
