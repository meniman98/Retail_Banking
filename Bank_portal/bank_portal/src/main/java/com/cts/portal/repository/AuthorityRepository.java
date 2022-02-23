package com.cts.portal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cts.portal.model.Authority;
import com.cts.portal.model.BankUser;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long> {
	Authority findByName(String name);
}
