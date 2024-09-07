package com.cesar31.root.application.ports.output;

import java.util.UUID;

public interface CurrentUserOutputPort {

    UUID getUserId();

    UUID getOrganizationId();
}
