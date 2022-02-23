package com.cts.Account.repo;

import com.cts.Account.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends
        JpaRepository<Customer, Long> {
}
