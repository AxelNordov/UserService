package com.example.userservicetest.service;

import com.example.userservicetest.entity.User;
import com.example.userservicetest.entity.UserGroup;
import com.example.userservicetest.mapper.UserMapper;
import com.example.userservicetest.repository.UserGroupRepository;
import com.example.userservicetest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import package_com.example.userservicetest.model.UserDto;
import package_com.example.userservicetest.model.UserGroupDto;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserGroupRepository userGroupRepository;

    @Autowired
    UserRepository userRepository;

    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> userMapper.toDto(user))
                .collect(Collectors.toList());
    }

    public UserDto getUserById(String id) {
        return userMapper.toDto(userRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public UserDto createUser(UserDto userDto) {
        User entity = userMapper.toEntity(userDto);
        return userMapper.toDto(userRepository.save(entity));
    }

    public UserDto updateUser(String id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());

//        List<UserGroupDto> userDtoGroups = userDto.getUserGroups();
//        if (userDtoGroups != null) {
//            Set<UserGroup> userGroups = user.getUserGroups();
//            userDtoGroups.forEach(group -> userGroups.add(
//                    userGroupRepository.findById(group.getUuid()).orElseThrow(EntityNotFoundException::new)));
//        }

        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
