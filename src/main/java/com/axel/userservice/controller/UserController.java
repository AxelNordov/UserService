package com.axel.userservice.controller;

import com.axel.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import package_com.example.userservicetest.api.UserControllerApi;
import package_com.example.userservicetest.model.UserDto;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController implements UserControllerApi {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @Override
    public ResponseEntity<UserDto> getUserById(UUID uuid) {
        return ResponseEntity.ok(userService.getById(uuid));
    }

    @Override
    public ResponseEntity<UserDto> createUser(UserDto userDto) {
        return ResponseEntity.ok(userService.create(userDto));
    }

    @Override
    public ResponseEntity<UserDto> updateUser(UUID uuid, UserDto userDto) {
        return ResponseEntity.ok(userService.update(uuid, userDto));
    }

    @Override
    public ResponseEntity<Void> deleteUser(UUID uuid) {
        userService.delete(uuid);
        return ResponseEntity.ok().build();
    }

}
