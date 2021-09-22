package com.example.userservicetest.mapper;

import com.example.userservicetest.entity.User;
import com.example.userservicetest.entity.UserGroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import package_com.example.userservicetest.model.UserDto;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = {UserGroupMapper.class})
public interface UserMapper {

//    @Mapping(target = "uuid", ignore = true)
    User map(UserDto userDto);

    Set<UserGroup> map(List<UUID> value);

}
