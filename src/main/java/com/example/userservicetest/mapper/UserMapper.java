package com.example.userservicetest.mapper;

import com.example.userservicetest.entity.User;
import org.mapstruct.Mapper;
import package_com.example.userservicetest.model.UserDto;

@Mapper
//@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userUserDto(User user);

}
