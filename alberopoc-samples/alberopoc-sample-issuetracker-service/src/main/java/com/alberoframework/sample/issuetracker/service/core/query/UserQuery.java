package com.alberoframework.sample.issuetracker.service.core.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

import com.alberoframework.component.query.contract.AbstractQuery;
import com.alberoframework.sample.issuetracker.service.core.entity.UserEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserQuery extends AbstractQuery<Optional<UserEntity>>{

    private String userId;
}
