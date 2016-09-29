package com.alberoframework.sample.issuetracker.service.core.value;

import com.alberoframework.lang.object.BaseObject;
import com.alberoframework.sample.issuetracker.service.core.entity.IssueEntity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
public class MembershipValue extends BaseObject{

    private String userId;
    private MembershipTypeValue membershipType;
}
