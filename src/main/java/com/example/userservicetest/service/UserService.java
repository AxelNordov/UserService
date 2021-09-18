package com.example.userservicetest.service;

import com.example.userservicetest.entity.User;
import com.example.userservicetest.mapper.UserMapper;
import com.example.userservicetest.repository.UserRepository;
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
    UserRepository userRepository;

    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> userMapper.userToUserDto(user))
                .collect(Collectors.toList());
    }

    public UserDto getUserById(String id) {
        return userMapper.userToUserDto(userRepository.findById(id).get());
    }

    public UserDto createUser(UserDto userDto) {
        System.out.println(userDto);
        User entity = userMapper.userDtoToUser(userDto);
        System.out.println(entity);
        return userMapper.userToUserDto(userRepository.save(entity));
    }

    public UserDto updateUser(String id, UserDto userDto) {
        userRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        userDto.setUuid(id);
        userRepository.save(userMapper.userDtoToUser(userDto));
        return userMapper.userToUserDto(userRepository.findById(id).get());
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
