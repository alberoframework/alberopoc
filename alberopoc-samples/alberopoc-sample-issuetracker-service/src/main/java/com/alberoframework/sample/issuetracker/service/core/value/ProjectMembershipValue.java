package com.alberoframework.sample.issuetracker.service.core.value;

import com.alberoframework.lang.object.BaseObject;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
public class ProjectMembershipValue extends BaseObject{

    private String userId;
    private ProjectMembershipTypeValue membershipType;
}
