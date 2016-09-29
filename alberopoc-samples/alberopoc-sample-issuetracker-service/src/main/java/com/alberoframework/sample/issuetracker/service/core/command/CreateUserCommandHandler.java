package com.alberoframework.sample.issuetracker.service.core.command;

import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alberoframework.component.command.gateway.ContextualizedCommandGateway;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.sample.issuetracker.component.command.handler.AbstractIssueTrackerVoidCommandHandler;
import com.alberoframework.sample.issuetracker.service.core.entity.UserEntity;
import com.alberoframework.sample.issuetracker.service.core.repository.UserRepository;

@Setter
@Component
public class CreateUserCommandHandler extends AbstractIssueTrackerVoidCommandHandler<CreateUserCommand> {
	@Autowired
	UserRepository utenteRepository;

	@Override
	protected void doHandle(CreateUserCommand command, ContextualizedQueryGateway queryGateway, ContextualizedCommandGateway commandGateway) {
		UserEntity utenteTirocinante = UserEntity.create(command.getUserId(), command.getUsername(), command.getPassword(), command.getRole());
		utenteRepository.save(utenteTirocinante);
	}

}
