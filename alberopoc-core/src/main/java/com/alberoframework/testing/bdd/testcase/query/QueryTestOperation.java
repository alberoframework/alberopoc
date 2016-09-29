package com.alberoframework.testing.bdd.testcase.query;

import com.alberoframework.testing.bdd.context.TestContext;
import com.alberoframework.testing.bdd.testcase.operation.TestOperation;

public interface QueryTestOperation<C extends TestContext<?>, OUT> extends TestOperation<C, OUT> {

}
