package com.alberoframework.sample.issuetracker.service.core.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

import com.alberoframework.domain.entity.contract.AbstractEntity;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter(AccessLevel.PACKAGE)
public class CommentEntity extends AbstractEntity<String> {

	private String projectId;
	private String issueId;
    @Id
    private String commentId;
    private String text;
    private String creatorUserId;

    @Override
    public String identity() {
        return getCommentId();
    }
}
