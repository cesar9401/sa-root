package com.cesar31.root.infrastructure.adapters.output.feignclient;

import com.cesar31.root.application.ports.output.ExistsOrgOutputPort;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "sa-organization")
@Component
public interface ExistsOrgFeignClientAdapter extends ExistsOrgOutputPort {

    @Override
    @GetMapping("/api/sa-organization/organizations/exists/{organizationId}")
    Boolean existsOrganizationById(@PathVariable("organizationId") UUID organizationId);
}
