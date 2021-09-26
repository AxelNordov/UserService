package com.example.userservicetest.service;

import com.example.userservicetest.entity.User;
import com.example.userservicetest.entity.UserGroup;
import com.example.userservicetest.mapper.UserGroupDtoMapper;
import com.example.userservicetest.mapper.UserGroupMapper;
import com.example.userservicetest.repository.UserGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import package_com.example.userservicetest.model.UserDto;
import package_com.example.userservicetest.model.UserGroupDto;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserGroupService {

    @Autowired
    UserGroupRepository userGroupRepository;
    @Autowired
    UserGroupMapper userGroupMapper;
    @Autowired
    UserGroupDtoMapper userGroupDtoMapper;

    public List<UserGroupDto> getAll() {
        return userGroupRepository.findAll()
                .stream()
                .map(userGroup -> userGroupDtoMapper.map(userGroup))
                .collect(Collectors.toList());
    }

    public UserGroupDto getById(UUID uuid) {
        return userGroupDtoMapper.map(userGroupRepository.findById(uuid).get());
    }

    public UserGroupDto create(UserGroupDto userGroupDto) {
        UserGroup entity = userGroupMapper.map(userGroupDto);
        return userGroupDtoMapper.map(userGroupRepository.save(entity));
    }

    public UserGroupDto update(UUID uuid, UserGroupDto userGroupDto) {
        userGroupRepository.findById(uuid)
                .orElseThrow(EntityNotFoundException::new);
        UserGroup currentUserGroup = userGroupMapper.map(userGroupDto);
        currentUserGroup.setUuid(uuid);
        return userGroupDtoMapper.map(userGroupRepository.save(currentUserGroup));
    }

    public void delete(UUID uuid) {
        userGroupRepository.deleteById(uuid);
    }
}
