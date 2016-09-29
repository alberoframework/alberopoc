package com.alberoframework.sample.issuetracker.service.core.query;

import lombok.*;

import java.util.Optional;

import com.alberoframework.component.query.contract.AbstractQuery;
import com.alberoframework.sample.issuetracker.service.core.entity.UserEntity;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter(AccessLevel.PACKAGE)
public class UserByUsernameQuery extends AbstractQuery<Optional<UserEntity>> {

    private String username;
}
