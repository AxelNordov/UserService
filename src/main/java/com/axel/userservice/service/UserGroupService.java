package com.axel.userservice.service;

import package_com.example.userservicetest.model.UserGroupDto;

import java.util.List;
import java.util.UUID;

public interface UserGroupService {

    List<UserGroupDto> getAll();

    UserGroupDto getById(UUID uuid);

    UserGroupDto create(UserGroupDto userGroupDto);

    UserGroupDto update(UUID uuid, UserGroupDto userGroupDto);

    void delete(UUID uuid);

}
