package com.alberoframework.testing.bdd.testcase.query;

import java.util.Optional;

import com.alberoframework.testing.bdd.context.TestContext;
import com.alberoframework.testing.bdd.testcase.operation.AbstractTestOperation;

public abstract class AbstractQueryTestOperationWithNullableOutput<C extends TestContext<?>, OUT>
		extends AbstractTestOperation<C, Optional<OUT>> implements QueryTestOperation<C, Optional<OUT>> {

	@Override
	public final Optional<OUT> execute(C context) {
		return Optional.ofNullable(doExecute(context));
	}

	protected abstract OUT doExecute(C context);

}
