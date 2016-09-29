package com.alberoframework.sample.issuetracker.service.core.query;

import com.alberoframework.component.query.contract.AbstractQuery;
import com.alberoframework.sample.issuetracker.service.core.entity.CommentEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentCollectionQuery extends AbstractQuery<Iterable<CommentEntity>>{

    private String issueId;
}
