package com.alberoframework.sample.issuetracker.service.core.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

import com.alberoframework.lang.VoidUnit;
import com.alberoframework.sample.issuetracker.component.command.contract.AbstractIssueTrackerCreateEntityCommand;
import com.alberoframework.sample.issuetracker.service.core.value.MembershipValue;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProjectCommand extends AbstractIssueTrackerCreateEntityCommand<VoidUnit> {

    private String projectId;
    private String name;
    private Set<MembershipValue> memberships;

    @Override
    public void assignIdentity(String identity) {
    	setProjectId(identity);
    }
	
    @Override
    public String identity() {
    	return getProjectId();
    }

	
	
}
