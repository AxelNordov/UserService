package com.axel.userservice.controller;

import com.axel.userservice.service.UserService;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import package_com.example.userservicetest.model.UserDto;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private static final UUID USER_UUID = UUID.fromString("6694ae43-2f57-409f-8ce3-b3a94141af01");

    @Mock
    private UserDto userDto;

    @Mock
    private UserDto createdUserDto;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void getUsers() {
        var result = userController.getUsers();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(userService).getAll();
    }

    @Test
    void getUserById_WhenExists_Ok() {
        var result = userController.getUserById(USER_UUID);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(userService).getById(USER_UUID);
    }

    @Test
    void createUser() {
        doReturn(createdUserDto).when(userService).create(userDto);
        var result = userController.createUser(userDto);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        AssertionsForClassTypes.assertThat(result.getBody()).isEqualTo(createdUserDto);
    }

    @Test
    void updateUser() {
        var result = userController.updateUser(USER_UUID, userDto);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(userService).update(USER_UUID, userDto);
    }

    @Test
    void deleteUser() {
        userController.deleteUser(USER_UUID);
        verify(userService).delete(USER_UUID);
    }

}
