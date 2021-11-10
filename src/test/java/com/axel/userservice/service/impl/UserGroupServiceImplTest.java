package com.axel.userservice.service.impl;

import com.axel.userservice.entity.UserGroup;
import com.axel.userservice.mapper.UserGroupDtoMapper;
import com.axel.userservice.mapper.UserGroupMapper;
import com.axel.userservice.repository.UserGroupRepository;
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

    private final static UUID USER_UUID = UUID.fromString("6694ae43-2f57-409f-8ce3-b3a94141af01");

    @Mock
    private UserGroupRepository userGroupRepository;

    @Mock
    private UserGroupMapper userGroupMapper;

    @Mock
    private UserGroupDtoMapper userGroupDtoMapper;

    @Mock
    private UserGroupDto userGroupDto;

    @Mock
    private UserGroupDto anotherUserGroupDto;

    @Mock
    private UserGroupDto persistedUserGroupDto;

    @Mock
    private UserGroup userGroup;

    @Mock
    private UserGroup existedUserGroup;

    @Mock
    private UserGroup anotherUserGroup;

    @Mock
    private UserGroup persistedUserGroup;

    @InjectMocks
    private UserGroupServiceImpl testedEntry;

    @Test
    void getAll_shouldReturnsList() {
        doReturn(List.of(userGroup, anotherUserGroup)).when(userGroupRepository).findAll();
        when(userGroupDtoMapper.map(userGroup)).thenReturn(userGroupDto);
        when(userGroupDtoMapper.map(anotherUserGroup)).thenReturn(anotherUserGroupDto);
        var result = testedEntry.getAll();
        assertThat(result).hasSize(2).containsExactly(userGroupDto, anotherUserGroupDto);
    }

    @Test
    void getById_whenUserGroupIsPresent_shouldReturnUserGroupDto() {
        doReturn(Optional.of(existedUserGroup)).when(userGroupRepository).findById(USER_UUID);
        doReturn(userGroupDto).when(userGroupDtoMapper).map(existedUserGroup);
        var result = testedEntry.getById(USER_UUID);
        assertThat(result).isEqualTo(userGroupDto);
    }

    @Test
    void getById_whenUserGroupIsAbsent_shouldThrowException() {
        doReturn(Optional.empty()).when(userGroupRepository).findById(USER_UUID);
        assertThatThrownBy(() -> testedEntry.getById(USER_UUID))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("there is no usergroup with id: 6694ae43-2f57-409f-8ce3-b3a94141af01");
    }

    @Test
    void create_shouldCreateUserGroupWithGeneratedUuid() {
        doReturn(userGroup).when(userGroupMapper).map(userGroupDto);
        doReturn(persistedUserGroup).when(userGroupRepository).save(userGroup);
        doReturn(persistedUserGroupDto).when(userGroupDtoMapper).map(persistedUserGroup);
        var result = testedEntry.create(userGroupDto);
        assertThat(result).isEqualTo(persistedUserGroupDto);
    }

    @Test
    void update_whenUserGroupIsPresent_shouldUpdateUserGroup() {
        doReturn(Optional.of(existedUserGroup)).when(userGroupRepository).findById(USER_UUID);
        doReturn(userGroup).when(userGroupMapper).map(anotherUserGroupDto, USER_UUID);
        doReturn(persistedUserGroup).when(userGroupRepository).save(userGroup);
        doReturn(persistedUserGroupDto).when(userGroupDtoMapper).map(persistedUserGroup);
        var result = testedEntry.update(USER_UUID, anotherUserGroupDto);
        assertThat(result).isEqualTo(persistedUserGroupDto);
    }

    @Test
    void update_whenUserGroupIsPresent_shouldThrowException() {
        doReturn(Optional.empty()).when(userGroupRepository).findById(USER_UUID);
        assertThatThrownBy(() -> testedEntry.update(USER_UUID, anotherUserGroupDto))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("there is no usergroup with id: 6694ae43-2f57-409f-8ce3-b3a94141af01");
    }

    @Test
    void delete_whenUserGroupIsPresent_shouldDeleteUserGroup() {
        testedEntry.delete(USER_UUID);
        verify(userGroupRepository).deleteById(USER_UUID);
    }

}
