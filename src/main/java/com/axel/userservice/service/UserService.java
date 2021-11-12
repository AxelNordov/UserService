package com.axel.userservice.service;

import package_com.example.userservicetest.model.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<UserDto> getAll();

    UserDto getById(UUID id);

    UserDto create(UserDto userDto);

    UserDto update(UUID uuid, UserDto userDto);

    void delete(UUID uuid);

}
