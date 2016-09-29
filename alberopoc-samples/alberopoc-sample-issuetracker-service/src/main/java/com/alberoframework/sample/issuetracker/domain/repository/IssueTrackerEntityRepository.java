package com.alberoframework.sample.issuetracker.domain.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.alberoframework.domain.entity.contract.Entity;

import java.io.Serializable;

public interface IssueTrackerEntityRepository<E extends Entity<ID>, ID extends Serializable> extends PagingAndSortingRepository<E, ID> {
}
