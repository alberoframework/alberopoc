package com.alberoframework.sample.issuetracker.service.core.repository;

import org.springframework.stereotype.Repository;

import com.alberoframework.sample.issuetracker.domain.repository.IssueTrackerEntityRepository;
import com.alberoframework.sample.issuetracker.service.core.entity.UserEntity;

@Repository
public interface UserRepository extends IssueTrackerEntityRepository<UserEntity, String>{

    UserEntity findOneByUsername(String username);
}
