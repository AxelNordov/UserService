package com.example.userservicetest.controller;

import com.example.userservicetest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import package_com.example.userservicetest.api.UserControllerApi;
import package_com.example.userservicetest.model.UserDto;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController implements UserControllerApi {
    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @Override
    public ResponseEntity<UserDto> getUserById(UUID uuid) {
        return ResponseEntity.ok(userService.getUserById(uuid));
    }

    @Override
    public ResponseEntity<UserDto> createUser(UserDto userDto) {
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @Override
    public ResponseEntity<UserDto> updateUser(UUID uuid, UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(uuid, userDto));
    }

    @Override
    public ResponseEntity<Void> deleteUser(UUID uuid) {
        userService.deleteUser(uuid);
        return ResponseEntity.ok().build();
    }

}
