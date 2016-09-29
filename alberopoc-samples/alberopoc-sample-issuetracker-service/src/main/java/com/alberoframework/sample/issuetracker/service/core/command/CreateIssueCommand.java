package com.alberoframework.sample.issuetracker.service.core.command;

import com.alberoframework.lang.VoidUnit;
import com.alberoframework.sample.issuetracker.component.command.contract.AbstractIssueTrackerCreateEntityCommand;
import com.alberoframework.sample.issuetracker.service.core.value.UserRoleValue;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateIssueCommand extends AbstractIssueTrackerCreateEntityCommand<VoidUnit> {

    private String issueId;
    private String projectId;

    private String title;
    private String description;

    public CreateIssueCommand(String projectId) {
        setProjectId(projectId);
    }

    @Override
    public void assignIdentity(String identity) {
    	setIssueId(identity);
    }
	
    @Override
    public String identity() {
    	return getIssueId();
    }

	
	
}
