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

    private final UserGroupRepository userGroupRepository;

    private final UserGroupMapper userGroupMapper;

    private final UserGroupDtoMapper userGroupDtoMapper;

    @Autowired
    public UserGroupServiceImpl(UserGroupRepository userGroupRepository,
                                UserGroupMapper userGroupMapper,
                                UserGroupDtoMapper userGroupDtoMapper) {
        this.userGroupRepository = userGroupRepository;
        this.userGroupMapper = userGroupMapper;
        this.userGroupDtoMapper = userGroupDtoMapper;
    }

    public List<UserGroupDto> getAll() {
        return userGroupRepository.findAll()
                .stream()
                .map(userGroupDtoMapper::map)
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
        if (!userGroupRepository.existsById(uuid)) {
            throw new EntityNotFoundException("there is no usergroup with id: " + uuid);
        }
        var updatedUserGroup = userGroupMapper.map(userGroupDto, uuid);
        return userGroupDtoMapper.map(userGroupRepository.save(updatedUserGroup));
    }

    public void delete(UUID uuid) {
        userGroupRepository.deleteById(uuid);
    }

}
