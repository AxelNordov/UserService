package com.example.userservicetest.controller;

import com.example.userservicetest.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import package_com.example.userservicetest.api.UserGroupControllerApi;
import package_com.example.userservicetest.model.UserGroupDto;

import java.util.List;

@RestController
public class UserGroupController implements UserGroupControllerApi {
    @Autowired
    UserGroupService userGroupService;

    @Override
    public ResponseEntity<List<UserGroupDto>> getUserGroups() {
        return ResponseEntity.ok(userGroupService.getUserGroups());
    }

    @Override
    public ResponseEntity<UserGroupDto> createUserGroup(UserGroupDto userGroupDto) {
        return ResponseEntity.ok(userGroupService.createUserGroup(userGroupDto));
    }
}
