package com.example.userservicetest.service;

import com.example.userservicetest.entity.User;
import com.example.userservicetest.entity.UserGroup;
import com.example.userservicetest.mapper.UserMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import package_com.example.userservicetest.model.UserDto;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    public UserDto getUser() {
        User user = new User();
        user.setUuid(UUID.randomUUID().toString());

        UserGroup userGroup = new UserGroup();
        userGroup.setName("guest!!");
        userGroup.setUuid(UUID.randomUUID().toString());

        user.setUserGroups(List.of(userGroup,userGroup));

        UserMapper userMapper = Mappers.getMapper(UserMapper.class);

        return userMapper.userUserDto(user);
    }

}
