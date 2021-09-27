package com.example.userservicetest.mapper;

import com.example.userservicetest.entity.UserGroup;
import org.mapstruct.Mapper;
import package_com.example.userservicetest.model.UserGroupDto;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserGroupDtoMapper {

    UserGroupDto map(UserGroup userGroup);

    default UserGroupDto map(UUID uuid) {
        UserGroupDto userGroupDto = new UserGroupDto();
        userGroupDto.setUuid(uuid);
        return userGroupDto;
    }

}
