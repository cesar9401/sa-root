package com.cesar31.root.infrastructure.adapters.output.persistence.mapper;

import com.cesar31.root.domain.model.User;
import com.cesar31.root.infrastructure.adapters.output.persistence.entity.AdmUser;
import org.mapstruct.Mapper;

@Mapper
public interface UserPersistenceMapper {

    User toUser(AdmUser admUser);

    AdmUser toAdmUser(User user);
}
