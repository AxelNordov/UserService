package com.example.userservicetest.mapper;

import com.example.userservicetest.entity.UserGroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import package_com.example.userservicetest.model.UserGroupDto;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserGroupMapper {

    @Mapping(target = "uuid", ignore = true)
    UserGroup map(UserGroupDto userGroupDto);

    UserGroup map(UUID value);

    Set<UserGroup> map(List<UUID> value);

    List<UUID> map(Set<UserGroup> value);

    UUID map(UserGroup value);

}
