package com.alberoframework.domain.repository.persistence.memory;
//package base.domain.repository.persistence.memory;
//
//import base.domain.entity.contract.Entity;
//
//public class InMemoryIncrementalIdRepository<E extends Entity<Long>> extends InMemoryRepository<E, Long> {
//
//	private Long identityCounter = 1L;
//	
//	@Override
//	public <S extends E> S save(S entity) {
//		if (entity.identity() == null) {
//			synchronized (this) {
//				entity.assignIdentity(identityCounter);
//				identityCounter++;
//			}
//		}
//		else {
//			if (!db.containsKey(entity.identity())) {
//				if (identityCounter <= entity.identity()) {
//					identityCounter = entity.identity() + 1;
//				}
//			}
//		}
//		db.put(entity.identity(), entity);
//		return entity;
//	}
//	
//}
