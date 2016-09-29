package com.alberoframework.domain.command;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.domain.entity.contract.Entity;

public interface CreateEntityCommand<R, ID> extends Command<R>, Entity<ID> {
	
	public void assignIdentity(ID identity);

}
