package com.alberoframework.sample.issuetracker.service.core.repository;

import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import com.alberoframework.sample.issuetracker.domain.repository.IssueTrackerEntityRepository;
import com.alberoframework.sample.issuetracker.service.core.entity.IssueEntity;
import com.alberoframework.sample.issuetracker.service.core.value.IssueRelatedUserValue;
import com.alberoframework.sample.issuetracker.service.core.value.IssueStatusValue;

import java.util.Collection;

@Repository
public interface IssueRepository extends IssueTrackerEntityRepository<IssueEntity, String>, QueryByExampleExecutor<IssueEntity> {

    Collection<IssueEntity> findAllByProjectIdAndStatusAndCategoryIdAndRelatedUser(String projectId, IssueStatusValue status, String issueCategoryEntity, IssueRelatedUserValue relatedUser);
}
