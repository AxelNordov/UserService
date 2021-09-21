package com.example.userservicetest.service;

import com.example.userservicetest.entity.UserGroup;
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

    public UserGroupDto createUserGroup(UserGroupDto userGroupDto) {
        UserGroup entity = userGroupMapper.toEntity(userGroupDto);
        return userGroupMapper.toDto(userGroupRepository.save(entity));
    }

    public List<UserGroupDto> getUserGroups() {
        return userGroupRepository.findAll()
                .stream()
                .map(userGroup -> userGroupMapper.toDto(userGroup))
                .collect(Collectors.toList());
    }
}
