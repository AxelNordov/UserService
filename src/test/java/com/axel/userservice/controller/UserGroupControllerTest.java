package com.axel.userservice.controller;

import com.axel.userservice.controller.UserGroupController;
import com.axel.userservice.service.UserGroupService;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import package_com.example.userservicetest.model.UserGroupDto;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserGroupControllerTest {

    private static final UUID USER_UUID = UUID.fromString("6694ae43-2f57-409f-8ce3-b3a94141af01");

    @Mock
    private UserGroupDto userGroupDto;

    @Mock
    private UserGroupDto createdUserGroupDto;

    @Mock
    private UserGroupService userGroupService;

    @InjectMocks
    private UserGroupController userGroupController;

    @Test
    void getUserGroups() {
        var result = userGroupController.getUserGroups();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(userGroupService).getAll();
    }

    @Test
    void getUserGroupById_WhenExists_Ok() {
        var result = userGroupController.getUserGroupById(USER_UUID);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(userGroupService).getById(USER_UUID);
    }

    @Test
    void createUserGroup() {
        doReturn(createdUserGroupDto).when(userGroupService).create(userGroupDto);
        var result = userGroupController.createUserGroup(userGroupDto);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        AssertionsForClassTypes.assertThat(result.getBody()).isEqualTo(createdUserGroupDto);
    }

    @Test
    void updateUserGroup() {
        var result = userGroupController.updateUserGroup(USER_UUID, userGroupDto);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(userGroupService).update(USER_UUID, userGroupDto);
    }

    @Test
    void deleteUserGroup() {
        userGroupController.deleteUserGroup(USER_UUID);
        verify(userGroupService).delete(USER_UUID);
    }

}
