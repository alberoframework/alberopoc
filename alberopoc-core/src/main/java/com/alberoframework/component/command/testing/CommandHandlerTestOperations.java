package com.alberoframework.component.command.testing;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.command.handler.CommandHandler;
import com.alberoframework.component.request.contract.RequestEnvelope;
import com.alberoframework.component.request.testing.RequestHandlerTestContext;
import com.alberoframework.testing.bdd.testcase.behavior.AbstractBehaviorTestOperation;

public class CommandHandlerTestOperations {

	public static class CommandHandlerTestOperation<CH extends CommandHandler<ENV, C, R>, ENV extends RequestEnvelope<C, R>, C extends Command<R>, R> extends AbstractBehaviorTestOperation<RequestHandlerTestContext<CH, ENV, C, R>, R> {
		
		private ENV command;

		public CommandHandlerTestOperation(ENV command) {
			this.command = command;
		}
		
		@Override
		public R doExecute(RequestHandlerTestContext<CH, ENV, C, R> context) {
			CH handler = context.requestHandler();
			R response = handler.handle(command);
			return response;
//			return (response instanceof Optional) ? (Optional<R>) response : (response instanceof VoidUnit) ? Optional.empty() : Optional.ofNullable(response);
		}

	}
	
}
