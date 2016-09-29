package com.alberoframework.sample.issuetracker.service.core.value;

import com.alberoframework.lang.object.BaseObject;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter(AccessLevel.PACKAGE)
public class IssueRelatedUserValue extends BaseObject {
    private String userId;
    private IssueRelationTypeValue type;
}
