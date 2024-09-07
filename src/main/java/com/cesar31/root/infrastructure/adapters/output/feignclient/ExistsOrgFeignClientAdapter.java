package com.cesar31.root.infrastructure.adapters.output.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "sa-organization")
public interface ExistsOrgFeignClientAdapter {

    @GetMapping("/api/sa-organization/organizations/exists/{organizationId}")
    Boolean existsOrgById(@PathVariable("organizationId") UUID organizationId);
}
