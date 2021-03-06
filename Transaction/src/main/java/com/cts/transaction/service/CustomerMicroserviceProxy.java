package com.cts.transaction.service;

import com.cts.transaction.model.CustomerSummary;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="${microservice.customer.name}")
public interface CustomerMicroserviceProxy {

    @GetMapping("${url.details.customer}")
    CustomerSummary getCustomerDetails(@RequestParam Long customerId, @RequestHeader(name = "Authorization", required = false) String bearerToken);
}
