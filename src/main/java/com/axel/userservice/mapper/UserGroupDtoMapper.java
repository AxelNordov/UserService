package com.axel.userservice.mapper;

import com.axel.userservice.entity.UserGroup;
import org.mapstruct.Mapper;
import package_com.example.userservicetest.model.UserGroupDto;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserGroupDtoMapper {

    UserGroupDto map(UserGroup userGroup);

    UserGroupDto map(UUID uuid);

    List<UUID> mapToUuidList(Collection<UserGroup> userGroups);

    default UUID mapToUuid(UserGroup userGroup) {
        return userGroup.getUuid();
    }

}
