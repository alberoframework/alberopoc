package com.alberoframework.sample.issuetracker.service.core.command;

import com.alberoframework.component.command.contract.AbstractCommand;
import com.alberoframework.lang.VoidUnit;
import com.alberoframework.sample.issuetracker.service.core.value.ProjectMembershipValue;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter(AccessLevel.PACKAGE)
public class AddProjectMembershipCommand extends AbstractCommand<VoidUnit> {

    private String projectId;
    private ProjectMembershipValue membership;
}
