package com.example.userservicetest.mapper;

import com.example.userservicetest.entity.User;
import org.mapstruct.Mapper;
import package_com.example.userservicetest.model.UserDto;

@Mapper(componentModel = "spring", uses = {UserGroupMapper.class})
public interface UserDtoMapper {

    UserDto map(User user);

}
