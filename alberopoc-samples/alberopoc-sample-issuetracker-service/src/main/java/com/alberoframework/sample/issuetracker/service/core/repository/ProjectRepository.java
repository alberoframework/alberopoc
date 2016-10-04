package com.alberoframework.sample.issuetracker.service.core.repository;

import org.springframework.stereotype.Repository;

import com.alberoframework.sample.issuetracker.domain.repository.IssueTrackerEntityRepository;
import com.alberoframework.sample.issuetracker.service.core.entity.ProjectEntity;

@Repository
public interface ProjectRepository extends IssueTrackerEntityRepository<ProjectEntity, String> {

}
