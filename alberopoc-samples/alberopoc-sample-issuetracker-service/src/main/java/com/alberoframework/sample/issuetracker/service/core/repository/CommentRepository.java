package com.alberoframework.sample.issuetracker.service.core.repository;

import org.springframework.stereotype.Repository;

import com.alberoframework.sample.issuetracker.domain.repository.IssueTrackerEntityRepository;
import com.alberoframework.sample.issuetracker.service.core.entity.CommentEntity;

import java.util.Collection;

@Repository
public interface CommentRepository extends IssueTrackerEntityRepository<CommentEntity, String> {

    Collection<CommentEntity> findByProjectIdAndIssueId(String projectId, String issueId);

}
