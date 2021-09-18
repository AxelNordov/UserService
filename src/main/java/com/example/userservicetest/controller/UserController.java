package com.example.userservicetest.controller;

import com.example.userservicetest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import package_com.example.userservicetest.api.UserControllerApi;
import package_com.example.userservicetest.model.UserDto;

import java.util.List;

@RestController
//@Api(value = "UserController", description = "the UserController!!!! API")
public class UserController implements UserControllerApi {
    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @Override
    public ResponseEntity<UserDto> getUserById(String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Override
    public ResponseEntity<UserDto> createUser(UserDto userDto) {
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @Override
    public ResponseEntity<UserDto> updateUser(String id, UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(id, userDto));
    }

    @Override
    public ResponseEntity<Void> deleteUser(String id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

}
