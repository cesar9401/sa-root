package com.cesar31.root.application.ports.output;

import java.util.UUID;

public interface ExistsOrgOutputPort {

    Boolean existsOrganizationById(UUID organizationId);
}
