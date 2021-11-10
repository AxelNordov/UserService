package com.axel.userservice.service.impl;

import com.axel.userservice.mapper.UserGroupDtoMapper;
import com.axel.userservice.mapper.UserGroupMapper;
import com.axel.userservice.repository.UserGroupRepository;
import com.axel.userservice.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import package_com.example.userservicetest.model.UserGroupDto;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserGroupServiceImpl implements UserGroupService {

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
        var userGroup = userGroupRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("there is no usergroup with id: " + uuid));
        return userGroupDtoMapper.map(userGroup);
    }

    public UserGroupDto create(UserGroupDto userGroupDto) {
        var entity = userGroupMapper.map(userGroupDto);
        return userGroupDtoMapper.map(userGroupRepository.save(entity));
    }

    public UserGroupDto update(UUID uuid, UserGroupDto userGroupDto) {
        userGroupRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("there is no usergroup with id: " + uuid));
        var updatedUserGroup = userGroupMapper.map(userGroupDto, uuid);
        return userGroupDtoMapper.map(userGroupRepository.save(updatedUserGroup));
    }

    public void delete(UUID uuid) {
        userGroupRepository.deleteById(uuid);
    }
}
