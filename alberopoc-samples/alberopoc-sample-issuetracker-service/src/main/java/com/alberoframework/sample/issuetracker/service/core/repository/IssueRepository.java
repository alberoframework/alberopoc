package com.alberoframework.sample.issuetracker.service.core.repository;

import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import com.alberoframework.sample.issuetracker.domain.repository.IssueTrackerEntityRepository;
import com.alberoframework.sample.issuetracker.service.core.entity.IssueEntity;

@Repository
public interface IssueRepository extends IssueTrackerEntityRepository<IssueEntity, String>, QueryByExampleExecutor<IssueEntity> {

	IssueEntity findByProjectIdAndIssueId(String projectId, String issueId);
	
}
