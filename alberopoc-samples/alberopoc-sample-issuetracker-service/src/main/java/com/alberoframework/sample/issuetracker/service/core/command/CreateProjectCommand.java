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
public class CreateProjectCommand extends AbstractIssueTrackerCreateEntityCommand<VoidUnit> {

    private String projectId;
    private String name;

    @Override
    public void assignIdentity(String identity) {
    	setProjectId(identity);
    }
	
    @Override
    public String identity() {
    	return getProjectId();
    }

	
	
}
