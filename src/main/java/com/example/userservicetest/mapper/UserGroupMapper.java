package com.example.userservicetest.mapper;

import com.example.userservicetest.entity.UserGroup;
import org.mapstruct.Mapper;
import package_com.example.userservicetest.model.UserGroupDto;

@Mapper(componentModel = "spring")
public interface UserGroupMapper {

    //    @Mapping(target = "name", ignore = true)
    UserGroupDto userGroupToUserGroupDto(UserGroup userGroup);

    UserGroup userGroupDtoToUserGroup(UserGroupDto userGroupDto);

}
