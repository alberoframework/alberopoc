package com.alberoframework.sample.issuetracker.service.core.entity;

import lombok.*;

import org.springframework.data.annotation.Id;

import com.alberoframework.domain.entity.contract.AbstractEntity;
import com.alberoframework.sample.issuetracker.service.core.value.MembershipValue;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
public class ProjectEntity extends AbstractEntity<String> {

    @Id
    private String projectId;
    private String name;
    private Set<MembershipValue> memberships;

    @Override
    public String identity() {
        return getProjectId();
    }

}
