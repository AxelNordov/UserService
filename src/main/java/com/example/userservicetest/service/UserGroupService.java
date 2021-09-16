package com.example.userservicetest.service;

import com.example.userservicetest.entity.UserGroup;
import com.example.userservicetest.mapper.UserGroupMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import package_com.example.userservicetest.model.UserGroupDto;

import java.util.UUID;

@Service
public class UserGroupService {

    @Autowired
    UserGroupMapper userGroupMapper;

    public UserGroupDto getUserGroup() {
        UserGroup userGroup = UserGroup.builder()
                .name("guest!!")
                .uuid(UUID.randomUUID().toString())
                .build();

        System.out.println("hello");

        return userGroupMapper.userGroupUserGroupDto(userGroup);
    }

}
