package com.axel.userservice.controller;

import com.axel.userservice.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import package_com.example.userservicetest.api.UserGroupControllerApi;
import package_com.example.userservicetest.model.UserGroupDto;

import java.util.List;
import java.util.UUID;

@RestController
public class UserGroupController implements UserGroupControllerApi {

    @Autowired
    UserGroupService userGroupService;

    @Override
    public ResponseEntity<List<UserGroupDto>> getUserGroups() {
        return ResponseEntity.ok(userGroupService.getAll());
    }

    @Override
    public ResponseEntity<UserGroupDto> createUserGroup(UserGroupDto userGroupDto) {
        return ResponseEntity.ok(userGroupService.create(userGroupDto));
    }

    @Override
    public ResponseEntity<UserGroupDto> getUserGroupById(UUID uuid) {
        return ResponseEntity.ok(userGroupService.getById(uuid));
    }

    @Override
    public ResponseEntity<UserGroupDto> updateUserGroup(UUID uuid, UserGroupDto userGroupDto) {
        return ResponseEntity.ok(userGroupService.update(uuid, userGroupDto));
    }

    @Override
    public ResponseEntity<Void> deleteUserGroup(UUID uuid) {
        userGroupService.delete(uuid);
        return ResponseEntity.ok().build();
    }
}
