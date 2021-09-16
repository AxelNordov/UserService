package com.example.userservicetest.service;

import com.example.userservicetest.entity.UserGroup;
import com.example.userservicetest.mapper.UserGroupMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import package_com.example.userservicetest.model.UserGroupDto;

import java.util.UUID;

@Service
public class UserGroupService {

    public UserGroupDto getUserGroup() {
        UserGroup userGroup = new UserGroup();
                userGroup.setName("guest!!");
                userGroup.setUuid(UUID.randomUUID().toString());

        System.out.println("hello");

        UserGroupMapper userGroupMapper = Mappers.getMapper(UserGroupMapper.class);
        return userGroupMapper.userGroupUserGroupDto(userGroup);
    }

}
