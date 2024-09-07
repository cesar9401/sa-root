package com.cesar31.root.infrastructure.adapters.output.clientrest;

import com.cesar31.root.application.ports.output.ExistsOrgOutputPort;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public class ExistsOrgClientAdapter implements ExistsOrgOutputPort {

    private final RestTemplate restTemplate;

    public ExistsOrgClientAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Boolean existsOrganizationById(UUID organizationId) {
        return restTemplate.getForObject("http://sa-organization/api/sa-organization/organizations/exists/" + organizationId, Boolean.class);
    }
}
