package com.cts.transaction.service;

import com.cts.transaction.model.RuleStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="rule-microservice", url="localhost:8083/test")
public interface RuleMicroserviceProxy {

    @GetMapping("${url.evaluate.rules}")
    public RuleStatus evaluateMinBal(double balance, String accountType);

    @GetMapping("${url.charges.rules}")
    public Float getServiceCharges();
}
