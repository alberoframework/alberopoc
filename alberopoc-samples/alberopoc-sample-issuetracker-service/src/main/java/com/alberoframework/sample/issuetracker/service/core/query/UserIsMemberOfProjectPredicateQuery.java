package com.alberoframework.sample.issuetracker.service.core.query;

import java.util.Optional;

import com.alberoframework.component.query.contract.AbstractPredicateQuery;
import com.alberoframework.sample.issuetracker.service.core.value.ProjectMembershipTypeValue;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter(AccessLevel.PACKAGE)
public class UserIsMemberOfProjectPredicateQuery extends AbstractPredicateQuery {
    
	private String userId;
	private String projectId;
	private Optional<ProjectMembershipTypeValue> membershipType;
	
	public UserIsMemberOfProjectPredicateQuery(String userId, String projectId) {
		this.userId = userId;
		this.projectId = projectId;
		this.membershipType = Optional.empty();
	}
	
	public UserIsMemberOfProjectPredicateQuery(String userId, String projectId, ProjectMembershipTypeValue membershipType) {
		this.userId = userId;
		this.projectId = projectId;
		this.membershipType = Optional.ofNullable(membershipType);
	}
	
}
