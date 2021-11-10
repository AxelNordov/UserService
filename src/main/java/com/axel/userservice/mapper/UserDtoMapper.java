package com.axel.userservice.mapper;

import com.axel.userservice.entity.User;
import org.mapstruct.Mapper;
import package_com.example.userservicetest.model.UserDto;

@Mapper(componentModel = "spring", uses = {UserGroupDtoMapper.class})
public interface UserDtoMapper {

    UserDto map(User user);

}
