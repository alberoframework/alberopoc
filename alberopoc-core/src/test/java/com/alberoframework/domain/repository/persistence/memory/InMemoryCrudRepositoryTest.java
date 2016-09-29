package com.alberoframework.domain.repository.persistence.memory;

import com.alberoframework.domain.repository.contract.CrudRepositoryTest;
import com.alberoframework.domain.repository.contract.CrudRepositoryTestStubs.EntityStubRepository;
import com.alberoframework.domain.repository.persistence.memory.InMemoryCrudRepositoryTestStubs.InMemoryEntityStubRepository;

public class InMemoryCrudRepositoryTest extends CrudRepositoryTest {

	@Override
	protected EntityStubRepository repository() {
		return new InMemoryEntityStubRepository();
	}


}
