package com.alberoframework.sample.issuetracker.component.command.handler;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.command.handler.SimpleAuthenticatedCommandHandler;

public interface IssueTrackerCommandHandler<C extends Command<R>, R> extends SimpleAuthenticatedCommandHandler<C, R> {

}
