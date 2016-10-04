package com.alberoframework.sample.issuetracker.service.core.entity;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;

import com.alberoframework.domain.entity.contract.AbstractEntity;
import com.alberoframework.sample.issuetracker.service.core.value.ProjectMembershipValue;
import com.google.common.base.Objects;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
public class ProjectEntity extends AbstractEntity<String> {

    @Id
    private String projectId;
    private String name;
    private Set<ProjectMembershipValue> memberships = new HashSet<>();

    public ProjectEntity(String projectId, String name) {
		this.projectId = projectId;
		this.name = name;
	}
    
    public void addMembership(ProjectMembershipValue membership) {
    	memberships.add(membership);
    }
    
    public boolean userIsMember(String userId) {
    	return   getMemberships()
    			.stream()
    			.anyMatch(m -> Objects.equal(m.getUserId(), userId));
    }
    
    public boolean containsMembership(ProjectMembershipValue membership) {
    	return getMemberships().contains(membership);
    }
    
    @Override
    public String identity() {
        return getProjectId();
    }

}
