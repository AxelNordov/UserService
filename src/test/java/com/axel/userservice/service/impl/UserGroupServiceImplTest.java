package com.axel.userservice.service.impl;

import com.axel.userservice.entity.UserGroup;
import com.axel.userservice.mapper.UserGroupDtoMapper;
import com.axel.userservice.mapper.UserGroupMapper;
import com.axel.userservice.repository.UserGroupRepository;
import com.axel.userservice.service.UserGroupService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import package_com.example.userservicetest.model.UserGroupDto;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserGroupServiceImplTest {

    private final static UUID USER_GROUP_UUID = UUID.randomUUID();
    private final static UUID ANOTHER_USER_GROUP_UUID = UUID.randomUUID();

    @Mock
    private UserGroupRepository userGroupRepository;

    @Mock
    private UserGroupMapper userGroupMapper;

    @Mock
    private UserGroupDtoMapper userGroupDtoMapper;

    @InjectMocks
    private UserGroupServiceImpl userGroupService;

    @Mock
    private UserGroupDto userGroupDto;

    @Mock
    private UserGroupDto anotherUserGroupDto;

    @Mock
    private UserGroupDto savedUserGroupDto;

    @Mock
    private UserGroup userGroup;

    @Mock
    private UserGroup anotherUserGroup;

    @Mock
    private UserGroup savedUserGroup;

    @Test
    void getAll_shouldReturnsList() {
        doReturn(List.of(userGroup, anotherUserGroup)).when(userGroupRepository).findAll();
        when(userGroupDtoMapper.map(userGroup)).thenReturn(userGroupDto);
        when(userGroupDtoMapper.map(anotherUserGroup)).thenReturn(anotherUserGroupDto);
        var result = userGroupService.getAll();
        assertThat(result).hasSize(2).containsExactly(userGroupDto, anotherUserGroupDto);
    }

    @Test
    void getById_whenUserIsPresent_shouldReturnUserDto() {
        doReturn(Optional.of(userGroup)).when(userGroupRepository).findById(USER_GROUP_UUID);
        doReturn(userGroupDto).when(userGroupDtoMapper).map(userGroup);
        var result = userGroupService.getById(USER_GROUP_UUID);
        assertThat(result).isEqualTo(userGroupDto);
    }

    @Test
    void getById_whenUserIsAbsent_shouldThrowException() {
        doThrow(new EntityNotFoundException()).when(userGroupRepository).findById(USER_GROUP_UUID);
        assertThatThrownBy(() -> userGroupService.getById(USER_GROUP_UUID)).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void create_shouldCreateUserWithGeneratedUuid() {
        doReturn(userGroup).when(userGroupMapper).map(userGroupDto);
        doReturn(savedUserGroup).when(userGroupRepository).save(userGroup);
        doReturn(savedUserGroupDto).when(userGroupDtoMapper).map(savedUserGroup);
        var result = userGroupService.create(userGroupDto);
        assertThat(result).isEqualTo(savedUserGroupDto);
    }

    @Test
    void update_whenUserIsPresent_shouldUpdateUser() {
        doReturn(Optional.of(userGroup)).when(userGroupRepository).findById(USER_GROUP_UUID);
        doReturn(userGroup).when(userGroupMapper).map(anotherUserGroupDto, USER_GROUP_UUID);
        doReturn(savedUserGroup).when(userGroupRepository).save(userGroup);
        doReturn(savedUserGroupDto).when(userGroupDtoMapper).map(savedUserGroup);
        var result = userGroupService.update(USER_GROUP_UUID, anotherUserGroupDto);
        assertThat(result).isEqualTo(savedUserGroupDto);
    }

    @Test
    void update_whenUserIsPresent_shouldThrowException() {
        doThrow(new RuntimeException()).when(userGroupRepository).findById(ANOTHER_USER_GROUP_UUID);
        assertThatThrownBy(() -> userGroupService.update(USER_GROUP_UUID, anotherUserGroupDto))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void delete_whenUserIsPresent_shouldDeleteUser() {
        doNothing().when(userGroupRepository).deleteById(USER_GROUP_UUID);
        userGroupService.delete(USER_GROUP_UUID);
        verify(userGroupRepository, times(1)).deleteById(USER_GROUP_UUID);
    }

    @Test
    void delete_whenUserIsAbsent_shouldThrowException() {
        doThrow(new RuntimeException()).when(userGroupRepository).deleteById(ANOTHER_USER_GROUP_UUID);
        assertThatThrownBy(() -> userGroupService.delete(ANOTHER_USER_GROUP_UUID)).isInstanceOf(RuntimeException.class);
    }
}