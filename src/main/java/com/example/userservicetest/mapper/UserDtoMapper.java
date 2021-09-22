package com.example.userservicetest.mapper;

import com.example.userservicetest.entity.User;
import com.example.userservicetest.entity.UserGroup;
import org.mapstruct.Mapper;
import package_com.example.userservicetest.model.UserDto;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    UserDto map(User user);

    List<UUID> map(Set<UserGroup> value);

    UUID map(UserGroup value);

}
