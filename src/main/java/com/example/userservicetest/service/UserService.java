package com.example.userservicetest.service;

import com.example.userservicetest.entity.User;
import com.example.userservicetest.entity.UserGroup;
import com.example.userservicetest.mapper.UserMapper;
import com.example.userservicetest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import package_com.example.userservicetest.model.UserDto;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRepository userRepository;

    public UserDto getUser() {
        UserGroup userGroup = UserGroup.builder()
                .name("guest!!")
                .uuid(UUID.randomUUID().toString())
                .build();
        User user = User.builder()
                .uuid(UUID.randomUUID().toString())
                .userGroups(List.of(userGroup,userGroup))
                .build();

        return userMapper.userToUserDto(user);
    }

    public User addUser(UserDto userDto) {
        return userRepository.save(userMapper.userDtoToUser(userDto));
    }

}
