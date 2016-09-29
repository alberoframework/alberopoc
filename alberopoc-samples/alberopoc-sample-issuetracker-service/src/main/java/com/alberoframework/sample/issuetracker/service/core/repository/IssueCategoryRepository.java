package com.alberoframework.sample.issuetracker.service.core.repository;

import org.springframework.stereotype.Repository;

import com.alberoframework.sample.issuetracker.domain.repository.IssueTrackerEntityRepository;
import com.alberoframework.sample.issuetracker.service.core.entity.IssueCategoryEntity;

@Repository
public interface IssueCategoryRepository extends IssueTrackerEntityRepository<IssueCategoryEntity, String>{
}
