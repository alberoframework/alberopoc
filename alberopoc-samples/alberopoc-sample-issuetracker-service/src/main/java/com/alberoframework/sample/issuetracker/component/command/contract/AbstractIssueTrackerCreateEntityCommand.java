package com.alberoframework.sample.issuetracker.component.command.contract;

import com.alberoframework.domain.command.AbstractSimpleCreateEntityCommand;

public abstract class AbstractIssueTrackerCreateEntityCommand<R> extends AbstractSimpleCreateEntityCommand<R> implements IssueTrackerCreateEntityCommand<R> {

}
