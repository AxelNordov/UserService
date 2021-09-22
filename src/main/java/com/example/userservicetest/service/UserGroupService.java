package com.example.userservicetest.service;

import com.example.userservicetest.entity.UserGroup;
import com.example.userservicetest.mapper.UserGroupDtoMapper;
import com.example.userservicetest.mapper.UserGroupMapper;
import com.example.userservicetest.repository.UserGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import package_com.example.userservicetest.model.UserGroupDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserGroupService {

    @Autowired
    UserGroupRepository userGroupRepository;

    @Autowired
    UserGroupMapper userGroupMapper;
    @Autowired
    UserGroupDtoMapper userGroupDtoMapper;

    public UserGroupDto createUserGroup(UserGroupDto userGroupDto) {
        UserGroup entity = userGroupMapper.map(userGroupDto);
        return userGroupDtoMapper.map(userGroupRepository.save(entity));
    }

    public List<UserGroupDto> getUserGroups() {
        return userGroupRepository.findAll()
                .stream()
                .map(userGroup -> userGroupDtoMapper.map(userGroup))
                .collect(Collectors.toList());
    }
}
