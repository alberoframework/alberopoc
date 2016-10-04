package com.alberoframework.sample.issuetracker.service.core.command;

import com.alberoframework.lang.VoidUnit;
import com.alberoframework.sample.issuetracker.component.command.contract.AbstractIssueTrackerCreateEntityCommand;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentCommand extends AbstractIssueTrackerCreateEntityCommand<VoidUnit> {

	private String projectId;
	private String issueId;
    private String commentId;
    private String text;

    @Override
    public void assignIdentity(String identity) {
    	setCommentId(identity);
    }
	
    @Override
    public String identity() {
    	return getCommentId();
    }

	
	
}
