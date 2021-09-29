package com.axel.userservice.service;

import com.axel.userservice.entity.User;
import com.axel.userservice.mapper.UserDtoMapper;
import com.axel.userservice.mapper.UserMapper;
import com.axel.userservice.repository.UserGroupRepository;
import com.axel.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> userDtoMapper.map(user))
                .collect(Collectors.toList());
    }

    public UserDto getById(UUID id) {
        return userDtoMapper.map(
                userRepository.findById(id)
                        .orElseThrow(EntityNotFoundException::new));
    }

    public UserDto create(UserDto userDto) {
        return userDtoMapper.map(
                userRepository.save(userMapper.map(userDto)));
    }

    public UserDto update(UUID uuid, UserDto userDto) {
        userRepository.findById(uuid)
                .orElseThrow(EntityNotFoundException::new);
        User currentUser = userMapper.map(userDto);
        currentUser.setUuid(uuid);
        return userDtoMapper.map(userRepository.save(currentUser));
    }

    public void delete(UUID uuid) {
        userRepository.deleteById(uuid);
    }
}
