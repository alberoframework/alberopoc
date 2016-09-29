package com.alberoframework.sample.issuetracker.component.query.handler;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.handler.SimpleAuthenticatedQueryHandler;

public interface IssueTrackerQueryHandler<Q extends Query<R>, R> extends SimpleAuthenticatedQueryHandler<Q, R> {

}
