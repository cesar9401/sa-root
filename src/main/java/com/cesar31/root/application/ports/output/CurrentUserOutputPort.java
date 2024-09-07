package com.cesar31.root.application.ports.output;

import java.util.Set;
import java.util.UUID;

public interface CurrentUserOutputPort {

    UUID getUserId();

    UUID getOrganizationId();

    Boolean hasRole(UUID roleId);

    Boolean hasAnyRole(Set<UUID> roleIds);
}
