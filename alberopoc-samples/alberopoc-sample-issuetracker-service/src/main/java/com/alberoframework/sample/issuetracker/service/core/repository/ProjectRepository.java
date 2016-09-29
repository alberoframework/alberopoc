package com.alberoframework.sample.issuetracker.service.core.repository;

import org.springframework.stereotype.Repository;

import com.alberoframework.sample.issuetracker.domain.repository.IssueTrackerEntityRepository;
import com.alberoframework.sample.issuetracker.service.core.entity.ProjectEntity;
import com.alberoframework.sample.issuetracker.service.core.entity.UserEntity;

import java.util.Collection;

@Repository
public interface ProjectRepository extends IssueTrackerEntityRepository<ProjectEntity, String> {

    Collection<ProjectEntity> findAllByMembershipsUserId(String userId);
}
