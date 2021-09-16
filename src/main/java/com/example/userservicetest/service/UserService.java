package com.example.userservicetest.service;

import com.example.userservicetest.entity.User;
import com.example.userservicetest.entity.UserGroup;
import com.example.userservicetest.mapper.UserMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import package_com.example.userservicetest.model.UserDto;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public UserDto getUser() {
        UserGroup userGroup = UserGroup.builder()
                .name("guest!!")
                .uuid(UUID.randomUUID().toString())
                .build();
        User user = User.builder()
                .uuid(UUID.randomUUID().toString())
                .userGroups(List.of(userGroup,userGroup))
                .build();

        return userMapper.userUserDto(user);
    }

}
