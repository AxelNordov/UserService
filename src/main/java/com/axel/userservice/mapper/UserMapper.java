package com.axel.userservice.mapper;

import com.axel.userservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import package_com.example.userservicetest.model.UserDto;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {UserGroupMapper.class})
public interface UserMapper {

    @Mapping(target = "uuid", ignore = true)
    User map(UserDto userDto);

    @Mapping(target = "uuid", source = "uuid")
    User map(UserDto userDto, UUID uuid);

}
