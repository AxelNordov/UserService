package com.axel.userservice.mapper;

import com.axel.userservice.entity.UserGroup;
import org.mapstruct.Mapper;
import package_com.example.userservicetest.model.UserGroupDto;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserGroupDtoMapper {

    UserGroupDto map(UserGroup userGroup);

    UserGroupDto map(UUID uuid);

}
