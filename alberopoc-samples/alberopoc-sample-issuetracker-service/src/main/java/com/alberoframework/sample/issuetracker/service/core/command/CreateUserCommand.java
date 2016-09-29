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
public class CreateUserCommand extends AbstractIssueTrackerCreateEntityCommand<VoidUnit> {

	private String userId;
	private String username;
    private String password;
	private UserRoleValue role;

    @Override
    public void assignIdentity(String identity) {
    	setUserId(identity);
    }
	
    @Override
    public String identity() {
    	return getUserId();
    }

	
	
}
