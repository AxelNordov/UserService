package com.axel.userservice.mapper;

import com.axel.userservice.entity.UserGroup;
import org.mapstruct.Mapper;
import package_com.example.userservicetest.model.UserGroupDto;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserGroupDtoMapper {

    UserGroupDto map(UserGroup userGroup);

    List<UUID> mapToUuidList(Collection<UserGroup> userGroups);

    default UUID mapToUuid(UserGroup userGroup) {
        return Optional.ofNullable(userGroup)
                .map(UserGroup::getUuid)
                .orElse(null);
    }

}
