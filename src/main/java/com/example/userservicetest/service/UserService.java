package com.example.userservicetest.service;

import com.example.userservicetest.entity.User;
import com.example.userservicetest.mapper.UserDtoMapper;
import com.example.userservicetest.mapper.UserMapper;
import com.example.userservicetest.repository.UserGroupRepository;
import com.example.userservicetest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import package_com.example.userservicetest.model.UserDto;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserDtoMapper userDtoMapper;

    @Autowired
    UserGroupRepository userGroupRepository;

    @Autowired
    UserRepository userRepository;

    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> userDtoMapper.map(user))
                .collect(Collectors.toList());
    }

    public UserDto getUserById(UUID id) {
        return userDtoMapper.map(
                userRepository.findById(id)
                        .orElseThrow(EntityNotFoundException::new));
    }

    public UserDto createUser(UserDto userDto) {
        return userDtoMapper.map(
                userRepository.save(userMapper.map(userDto)));
    }

    @Transactional
    public UserDto updateUser(UUID uuid, UserDto userDto) {
        User user = userRepository.findById(uuid)
                .orElseThrow(EntityNotFoundException::new);
        User currentUser = userMapper.map(userDto);
        currentUser.setUuid(uuid);
        return userDtoMapper.map(userRepository.save(user));
    }

    public void deleteUser(UUID uuid) {
        userRepository.deleteById(uuid);
    }
}
