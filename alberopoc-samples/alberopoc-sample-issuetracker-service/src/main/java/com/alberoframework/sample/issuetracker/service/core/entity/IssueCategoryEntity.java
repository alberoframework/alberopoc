package com.alberoframework.sample.issuetracker.service.core.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

import com.alberoframework.domain.entity.contract.AbstractEntity;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter(AccessLevel.PACKAGE)
public class IssueCategoryEntity extends AbstractEntity<String> {

    @Id
    private String issueCategoryId;
    private String name;

    @Override
    public String identity() {
        return getIssueCategoryId();
    }
}
