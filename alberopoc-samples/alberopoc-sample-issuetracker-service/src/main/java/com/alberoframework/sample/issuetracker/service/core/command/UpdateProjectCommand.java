package com.alberoframework.sample.issuetracker.service.core.command;

import com.alberoframework.component.command.contract.AbstractCommand;
import com.alberoframework.lang.VoidUnit;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
public class UpdateProjectCommand extends AbstractCommand<VoidUnit> {

    private String projectId;
    private String name;

    public UpdateProjectCommand(String projectId) {
        this.projectId = projectId;
    }
}
