package com.axel.userservice.service.impl;

import com.axel.userservice.mapper.UserDtoMapper;
import com.axel.userservice.mapper.UserMapper;
import com.axel.userservice.repository.UserRepository;
import com.axel.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import package_com.example.userservicetest.model.UserDto;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final UserDtoMapper userDtoMapper;

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserMapper userMapper,
                           UserDtoMapper userDtoMapper,
                           UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userDtoMapper = userDtoMapper;
        this.userRepository = userRepository;
    }

    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userDtoMapper::map)
                .collect(Collectors.toList());
    }

    public UserDto getById(UUID uuid) {
        var user = userRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("there is no user with id: " + uuid));
        return userDtoMapper.map(user);
    }

    public UserDto create(UserDto userDto) {
        var newUser = userMapper.map(userDto);
        return userDtoMapper.map(userRepository.save(newUser));
    }

    public UserDto update(UUID uuid, UserDto userDto) {
        if (!userRepository.existsById(uuid)) {
            throw new EntityNotFoundException("there is no user with id: " + uuid);
        }
        var updatedUser = userMapper.map(userDto, uuid);
        return userDtoMapper.map(userRepository.save(updatedUser));
    }

    public void delete(UUID uuid) {
        userRepository.deleteById(uuid);
    }

}
