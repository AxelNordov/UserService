package com.example.userservicetest.controller;

import com.example.userservicetest.service.UserGroupService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import package_com.example.userservicetest.api.UserGroupControllerApi;
import package_com.example.userservicetest.model.UserGroupDto;

import java.util.List;

@RestController
@Api(value = "UserGroupController", description = "the UserGroupController!!!! API")
public class UserGroupController implements UserGroupControllerApi {
    @Autowired
    UserGroupService userGroupService;

    @Override
    public ResponseEntity<List<UserGroupDto>> getAllUserGroups() {
        UserGroupDto userGroupDto = userGroupService.getUserGroup();
        System.out.println(userGroupDto);
        return ResponseEntity.ok(List.of());
    }

}
