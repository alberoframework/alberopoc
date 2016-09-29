package com.alberoframework.sample.issuetracker.service.core.entity;

import lombok.*;

import org.springframework.data.annotation.Id;

import com.alberoframework.core.validation.Validation;
import com.alberoframework.domain.entity.contract.AbstractEntity;
import com.alberoframework.sample.issuetracker.service.core.value.IssueRelatedUserValue;
import com.alberoframework.sample.issuetracker.service.core.value.IssueRelationTypeValue;
import com.alberoframework.sample.issuetracker.service.core.value.IssueStatusValue;

import java.util.Arrays;
import java.util.List;

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

        issue.setRelatedUser(Arrays.asList(
            new IssueRelatedUserValue(creatorUserId, IssueRelationTypeValue.CREATOR)
        ));

        return issue;
    }

    @Id
    private String issueId;

    private String projectId;

    private String title;
    private String categoryId;
    private String description;

    private IssueStatusValue status;

    private List<IssueRelatedUserValue> relatedUser;

    public void assign(String assignedUserId) {
        Validation.validate(assignedUserId != null, IllegalArgumentException::new, "assignedUserId can't be null");
        Validation.validate(status != IssueStatusValue.TODO, IllegalArgumentException::new, "Can't assign a issue in status", status);

        this.relatedUser.add(new IssueRelatedUserValue(assignedUserId, IssueRelationTypeValue.ASSIGNEE));
        this.status = IssueStatusValue.IN_PROGRESS;
    }

    public void watch(String watcherUserId) {
        Validation.validate(watcherUserId != null, IllegalArgumentException::new, "watcherUserId can't be null");

        this.relatedUser.add(new IssueRelatedUserValue(watcherUserId, IssueRelationTypeValue.WATCHER));
    }

    public void close() {
        Validation.validate(status != IssueStatusValue.IN_PROGRESS, IllegalArgumentException::new, "Can't close a issue in status", status);

        this.status = IssueStatusValue.DONE;
    }

    @Override
    public String identity() {
        return getIssueId();
    }
}
