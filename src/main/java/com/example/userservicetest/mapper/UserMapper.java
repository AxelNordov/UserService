package com.example.userservicetest.mapper;

import com.example.userservicetest.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.jackson.nullable.JsonNullable;
import package_com.example.userservicetest.model.UserDto;

@Mapper(componentModel = "spring", uses = {UserGroupMapper.class})
public interface UserMapper {

    @Mapping(target = "uuid", ignore = true)
    User map(UserDto userDto);

    default String map(JsonNullable<UserDto.StatusEnum> value) {
        return value.get().getValue().toUpperCase();
    }

}
