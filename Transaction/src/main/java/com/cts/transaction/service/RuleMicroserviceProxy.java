package com.cts.transaction.service;

import com.cts.transaction.model.RuleStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="${microservice.rules.name}", url="${microservice.rules.url}")
public interface RuleMicroserviceProxy {

    @GetMapping("${url.evaluate.rules}")
    RuleStatus evaluateMinBal(@RequestParam double balance, @RequestParam String accountType);

    @GetMapping("${url.charges.rules}")
    Float getServiceCharges();
}
