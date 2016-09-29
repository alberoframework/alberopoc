package com.alberoframework.sample.issuetracker.service.core.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

import com.alberoframework.component.query.contract.AbstractQuery;
import com.alberoframework.sample.issuetracker.service.core.entity.IssueCategoryEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IssueCategoryQuery extends AbstractQuery<Optional<IssueCategoryEntity>>{

    private String issueCategoryId;
}
