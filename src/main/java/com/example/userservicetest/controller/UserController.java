package com.example.userservicetest.controller;

import com.example.userservicetest.service.UserGroupService;
import com.example.userservicetest.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import package_com.example.userservicetest.api.UserControllerApi;
import package_com.example.userservicetest.model.UserDto;

import java.util.List;
import java.util.UUID;

@RestController
@Api(value = "UserController", description = "the UserController!!!! API")
public class UserController implements UserControllerApi {
    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<List<UserDto>> getAllUsers() {
        UserDto user = new UserDto();
        user.setUuid(UUID.randomUUID().toString());
        user.setFirstName("Grigoriy");
        user.setLastName("Kovalenko");
        user.setEmail("grig@gamil.com");
        return ResponseEntity.ok(List.of(user));
    }

    @Override
    public ResponseEntity<UserDto> getUser(String id) {
        UserDto user = userService.getUser();
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<Void> deleteUser(String id) {
        return ResponseEntity.noContent().build();
    }
}
