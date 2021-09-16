package com.example.userservicetest.mapper;

import com.example.userservicetest.entity.UserGroup;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import package_com.example.userservicetest.model.UserGroupDto;

@Mapper
//@Mapper(componentModel = "spring")
public interface UserGroupMapper {

//    UserGroupMapper INSTANCE = Mappers.getMapper(UserGroupMapper.class);

    UserGroupDto userGroupUserGroupDto(UserGroup userGroup);

}
