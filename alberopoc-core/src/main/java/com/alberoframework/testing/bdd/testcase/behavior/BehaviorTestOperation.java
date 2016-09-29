package com.alberoframework.testing.bdd.testcase.behavior;

import com.alberoframework.testing.bdd.context.TestContext;
import com.alberoframework.testing.bdd.testcase.operation.TestOperation;

public interface BehaviorTestOperation<C extends TestContext<?>, OUT> extends TestOperation<C, OUT> {

}
