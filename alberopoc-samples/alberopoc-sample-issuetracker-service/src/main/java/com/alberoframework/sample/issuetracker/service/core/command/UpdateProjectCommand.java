package com.alberoframework.sample.issuetracker.service.core.command;

import lombok.*;

import java.util.List;
import java.util.Set;

import com.alberoframework.component.command.contract.AbstractCommand;
import com.alberoframework.lang.VoidUnit;
import com.alberoframework.sample.issuetracker.service.core.value.MembershipValue;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
public class UpdateProjectCommand extends AbstractCommand<VoidUnit> {

    private String projectId;
    private String name;
    private Set<MembershipValue> memberships;

    public UpdateProjectCommand(String projectId) {
        this.projectId = projectId;
    }
}
