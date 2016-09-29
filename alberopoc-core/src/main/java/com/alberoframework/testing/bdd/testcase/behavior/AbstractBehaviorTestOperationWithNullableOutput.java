package com.alberoframework.testing.bdd.testcase.behavior;

import java.util.Optional;

import com.alberoframework.testing.bdd.context.TestContext;
import com.alberoframework.testing.bdd.testcase.operation.AbstractTestOperation;

public abstract class AbstractBehaviorTestOperationWithNullableOutput<C extends TestContext<?>, OUT> extends AbstractTestOperation<C, Optional<OUT>> implements BehaviorTestOperation<C, Optional<OUT>> {

		@Override
		public final Optional<OUT> execute(C context) {
			return Optional.ofNullable(doExecute(context));
		}
		
		protected abstract OUT doExecute(C context);
			
	
}
