package com.alberoframework.component.command;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.command.gateway.ContextualizedCommandGateway;
import com.alberoframework.component.command.handler.AbstractSimpleAuthenticatedCommandHandler;
import com.alberoframework.component.command.handler.AbstractSimpleAuthenticatedVoidCommandHandler;
import com.alberoframework.component.command.handler.AbstractSimpleCommandHandler;
import com.alberoframework.component.command.handler.AbstractSimpleVoidCommandHandler;
import com.alberoframework.component.common.CommonTestStubs.EntityForRequestTestStub;
import com.alberoframework.component.common.CommonTestStubs.EntityForRequestTestStub2;
import com.alberoframework.component.common.CommonTestStubs.EntityForRequestTestStub2Repository;
import com.alberoframework.component.common.CommonTestStubs.EntityForRequestTestStubRepository;
import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.lang.object.BaseObject;

public class CommandHandlerPocTestStubs {

	public static class CreateEntityForRequestTestStubCommand extends BaseObject implements Command<Long> {
			Long   id;
		 	String property1;
		 	
			public CreateEntityForRequestTestStubCommand(Long id, String property1) {
				this.id = id;
				this.property1 = property1;
			}

			public Long getId() {
				return id;
			}

			public String getProperty1() {
				return property1;
			}
		
			public void setProperty1(String property1) {
				this.property1 = property1;
			}
		 
	}
	
	public static class CreateEntityForRequestTestStubCommandHandler extends AbstractSimpleCommandHandler<CreateEntityForRequestTestStubCommand, Long> {

		private EntityForRequestTestStubRepository entityForRequestTestStubRepository;
		
//		public CreateEntityForRequestTestStubCommandHandler(QueryGateway queryGateway, CommandGateway commandGateway,
//				EntityForRequestTestStubRepository entityForRequestTestStubRepository) {
//			this.entityForRequestTestStubRepository = entityForRequestTestStubRepository;
//		}

		@Override
		public Long doHandle(CreateEntityForRequestTestStubCommand command, ContextualizedQueryGateway queryGateway,
				ContextualizedCommandGateway commandGateway) {
			EntityForRequestTestStub entity = new EntityForRequestTestStub(command.getId(), command.getProperty1());
			entity = entityForRequestTestStubRepository.save(entity);
			return entity.getId();
		}
		
		public void setEntityForRequestTestStubRepository(
				EntityForRequestTestStubRepository entityForRequestTestStubRepository) {
			this.entityForRequestTestStubRepository = entityForRequestTestStubRepository;
		}
		
	}
	
	
	public static class CreateEntity2AndModifyEntity1Command extends BaseObject implements Command<com.alberoframework.lang.VoidUnit> {
		Long entity1Id;
		Long entity2Id;
	 	String property1;
	 	
		public CreateEntity2AndModifyEntity1Command(Long entity1Id, Long entity2Id, String property1) {
			this.entity1Id = entity1Id;
			this.entity2Id = entity2Id;
			this.property1 = property1;
		}
		
		public Long getEntity2Id() {
			return entity2Id;
		}
		
		public Long getEntity1Id() {
			return entity1Id;
		}

		public void setEntity1Id(Long entity1Id) {
			this.entity1Id = entity1Id;
		}

		public String getProperty1() {
			return property1;
		}
	
		public void setProperty1(String property1) {
			this.property1 = property1;
		}
	 
	}

	public static class CreateEntity2AndModifyEntity1CommandHandler extends AbstractSimpleVoidCommandHandler<CreateEntity2AndModifyEntity1Command> {
	
		private EntityForRequestTestStubRepository entityForRequestTestStubRepository;
		
		private EntityForRequestTestStub2Repository entityForRequestTestStub2Repository;
	
		@Override
		public void doHandle(CreateEntity2AndModifyEntity1Command command, ContextualizedQueryGateway queryGateway,
				ContextualizedCommandGateway commandGateway) {
			EntityForRequestTestStub2 entity2 = new EntityForRequestTestStub2(command.getEntity2Id(), command.getProperty1());
			entityForRequestTestStub2Repository.save(entity2);
			
			EntityForRequestTestStub entity1 = entityForRequestTestStubRepository.findById(command.getEntity1Id());
			
			entity1.setProperty1(command.getProperty1());
			
			entityForRequestTestStubRepository.save(entity1);
			
		}

		public void setEntityForRequestTestStubRepository(
				EntityForRequestTestStubRepository entityForRequestTestStubRepository) {
			this.entityForRequestTestStubRepository = entityForRequestTestStubRepository;
		}

		public void setEntityForRequestTestStub2Repository(
				EntityForRequestTestStub2Repository entityForRequestTestStub2Repository) {
			this.entityForRequestTestStub2Repository = entityForRequestTestStub2Repository;
		}
		
	}
	
	public static class CreateEntity2AndModifyEntity1AuthenticatedCommandHandler extends AbstractSimpleAuthenticatedVoidCommandHandler<CreateEntity2AndModifyEntity1Command> {
		
		private EntityForRequestTestStubRepository entityForRequestTestStubRepository;
		
		private EntityForRequestTestStub2Repository entityForRequestTestStub2Repository;
	
		@Override
		public void doHandle(CreateEntity2AndModifyEntity1Command command, ContextualizedQueryGateway queryGateway,
				ContextualizedCommandGateway commandGateway) {
			EntityForRequestTestStub2 entity2 = new EntityForRequestTestStub2(command.getEntity2Id(), command.getProperty1());
			entityForRequestTestStub2Repository.save(entity2);
			
			EntityForRequestTestStub entity1 = entityForRequestTestStubRepository.findById(command.getEntity1Id());
			
			entity1.setProperty1(command.getProperty1());
			
			entityForRequestTestStubRepository.save(entity1);
			
		}

		public void setEntityForRequestTestStubRepository(
				EntityForRequestTestStubRepository entityForRequestTestStubRepository) {
			this.entityForRequestTestStubRepository = entityForRequestTestStubRepository;
		}

		public void setEntityForRequestTestStub2Repository(
				EntityForRequestTestStub2Repository entityForRequestTestStub2Repository) {
			this.entityForRequestTestStub2Repository = entityForRequestTestStub2Repository;
		}
		
	}
	
	
	
	public static class CreateEntityForRequestTestStubAndSendRequestsCommand extends BaseObject implements Command<Long> {
		Long id;
	 	String property1;
	 	
		public CreateEntityForRequestTestStubAndSendRequestsCommand(Long id, String property1) {
			this.id = id;
			this.property1 = property1;
		}
		
		public Long getId() {
			return id;
		}

		public String getProperty1() {
			return property1;
		}
	
		public void setProperty1(String property1) {
			this.property1 = property1;
		}
	 
	}
	
	public static class AnotherCommand extends BaseObject implements Command<Integer> {
		String string;

		public AnotherCommand(String string) {
			this.string = string;
		}
		
		public String getString() {
			return string;
		}
		
		public void setString(String string) {
			this.string = string;
		}
		
	}
	
	public static class AnotherCommand2 extends BaseObject implements Command<Integer> {
		String string;

		public AnotherCommand2(String string) {
			this.string = string;
		}
		
		public String getString() {
			return string;
		}
		
		public void setString(String string) {
			this.string = string;
		}
		
	}
	
	public static class AQuery extends BaseObject implements Query<String> {
		private Integer integer;

		public AQuery(Integer integer) {
			this.integer = integer;
		}
		
		public Integer getInteger() {
			return integer;
		}
		
	}

	public static class CreateEntityForRequestTestStubAndSendRequestsCommandHandler extends AbstractSimpleCommandHandler<CreateEntityForRequestTestStubAndSendRequestsCommand, Long> {
	
		private EntityForRequestTestStubRepository entityForRequestTestStubRepository;
		
		@Override
		public Long doHandle(CreateEntityForRequestTestStubAndSendRequestsCommand command, ContextualizedQueryGateway queryGateway, ContextualizedCommandGateway commandGateway) {
			EntityForRequestTestStub entity = new EntityForRequestTestStub(command.getId(), command.getProperty1());
			entity = entityForRequestTestStubRepository.save(entity);
			Integer output = 0;
			output += commandGateway.handle(new AnotherCommand("someString"));
			output += commandGateway.handle(new AnotherCommand2("someString2"));
			output += commandGateway.handle(new AnotherCommand("someString"));
			output += queryGateway.handle(new AQuery(2)).length();
			output += entity.getId().intValue();
			return output.longValue();
		}
		
		public void setEntityForRequestTestStubRepository(
				EntityForRequestTestStubRepository entityForRequestTestStubRepository) {
			this.entityForRequestTestStubRepository = entityForRequestTestStubRepository;
		}
		
	}
	
	public static class CreateEntityForRequestTestStubAndSendRequestsAuthenticatedCommandHandler extends AbstractSimpleAuthenticatedCommandHandler<CreateEntityForRequestTestStubAndSendRequestsCommand, Long> {
		
		private EntityForRequestTestStubRepository entityForRequestTestStubRepository;
		
		@Override
		public Long doHandle(CreateEntityForRequestTestStubAndSendRequestsCommand command, ContextualizedQueryGateway queryGateway, ContextualizedCommandGateway commandGateway) {
			EntityForRequestTestStub entity = new EntityForRequestTestStub(command.getId(), command.getProperty1());
			entity = entityForRequestTestStubRepository.save(entity);
			Integer output = 0;
			output += commandGateway.handle(new AnotherCommand("someString"));
			output += commandGateway.handle(new AnotherCommand2("someString2"));
			output += commandGateway.handle(new AnotherCommand("someString"));
			output += queryGateway.handle(new AQuery(2)).length();
			output += entity.getId().intValue();
			return output.longValue();
		}
		
		public void setEntityForRequestTestStubRepository(
				EntityForRequestTestStubRepository entityForRequestTestStubRepository) {
			this.entityForRequestTestStubRepository = entityForRequestTestStubRepository;
		}
		
	}
	
}
