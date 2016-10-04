package com.alberoframework.sample.issuetracker.service.core.entity;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;

import com.alberoframework.core.validation.Validation;
import com.alberoframework.domain.entity.contract.AbstractEntity;
import com.alberoframework.sample.issuetracker.service.core.value.IssueStatusValue;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IssueEntity extends AbstractEntity<String> {

    public static IssueEntity create(String projectId, String issueId, String title, String description, String creatorUserId) {
        Validation.validate(projectId != null, IllegalArgumentException::new, "projectId can't be null");
        Validation.validate(issueId != null, IllegalArgumentException::new, "issueId can't be null");
        Validation.validate(title != null, IllegalArgumentException::new, "title can't be null");
        Validation.validate(description != null, IllegalArgumentException::new, "description can't be null");
        Validation.validate(creatorUserId != null, IllegalArgumentException::new, "creatorUserId can't be null");

        IssueEntity issue = new IssueEntity();
        issue.setProjectId(projectId);
        issue.setIssueId(issueId);
        issue.setTitle(title);
        issue.setDescription(description);
        issue.setCreatorUserId(creatorUserId);
        issue.setStatus(IssueStatusValue.TODO);

        return issue;
    }
	
    private String projectId;
    
    @Id
    private String issueId;

    private String title;
    
    private String description;

    private String creatorUserId;
    
    private IssueStatusValue status;
    
    private Set<String> assignedUserIds = new HashSet<>();
    
    public void assign(String assignedUserId) {
        Validation.validate(assignedUserId != null, IllegalArgumentException::new, "assignedUserId can't be null");
        this.assignedUserIds.add(assignedUserId);
    }

    public void setInProgress() {
        Validation.validate(status == IssueStatusValue.TODO, IllegalStateException::new, "Can't change issue to 'in progress' if status is not 'TODO'", status);
        this.status = IssueStatusValue.IN_PROGRESS;
    }
    
    public void close() {
        Validation.validate(status == IssueStatusValue.IN_PROGRESS, IllegalStateException::new, "Can't close an issue if status is not 'IN PROGRESS'", status);
        this.status = IssueStatusValue.CLOSED;
    }

    @Override
    public String identity() {
        return getIssueId();
    }
}
