package com.axel.userservice.mapper;

import com.axel.userservice.entity.UserGroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import package_com.example.userservicetest.model.UserGroupDto;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserGroupMapper {

    @Mapping(target = "uuid", ignore = true)
    UserGroup map(UserGroupDto userGroupDto);

    @Mapping(target = "uuid", source = "uuid")
    UserGroup map(UserGroupDto userGroupDto, UUID uuid);

    UserGroup map(UUID uuid);

    Set<UserGroup> mapToUserGroupList(Collection<UUID> uuids);

}
