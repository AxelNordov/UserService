package com.axel.userservice.service.impl;

import com.axel.userservice.entity.UserGroup;
import com.axel.userservice.mapper.UserGroupDtoMapper;
import com.axel.userservice.mapper.UserGroupMapper;
import com.axel.userservice.repository.UserGroupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import package_com.example.userservicetest.model.UserGroupDto;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserGroupServiceImplTest {

    @Mock
    UserGroupRepository userGroupRepository;

    @Mock
    UserGroupMapper userGroupMapper;

    @Mock
    UserGroupDtoMapper userGroupDtoMapper;

    @InjectMocks
    UserGroupServiceImpl userGroupService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll_shouldReturnsList() {
        var uuidA = UUID.randomUUID();
        var uuidB = UUID.randomUUID();
        var uuidC = UUID.randomUUID();
        var userGroupA = UserGroup.builder().uuid(uuidA).name("A").build();
        var userGroupB = UserGroup.builder().uuid(uuidB).name("B").build();
        var userGroupC = UserGroup.builder().uuid(uuidC).name("C").build();
        var userGroupList = List.of(userGroupA, userGroupB, userGroupC);
        when(userGroupRepository.findAll()).thenReturn(userGroupList);

        var userGroupDtoA = new UserGroupDto();
        userGroupDtoA.setUuid(uuidA);
        userGroupDtoA.setName("A");
        var userGroupDtoB = new UserGroupDto();
        userGroupDtoB.setUuid(uuidB);
        userGroupDtoB.setName("B");
        var userGroupDtoC = new UserGroupDto();
        userGroupDtoC.setUuid(uuidC);
        userGroupDtoC.setName("C");
        when(userGroupDtoMapper.map(userGroupA)).thenReturn(userGroupDtoA);
        when(userGroupDtoMapper.map(userGroupB)).thenReturn(userGroupDtoB);
        when(userGroupDtoMapper.map(userGroupC)).thenReturn(userGroupDtoC);
        var userGroupDtoList = List.of(userGroupDtoA, userGroupDtoB, userGroupDtoC);

        var userGroupDtoListResult = userGroupService.getAll();

        assertEquals(3, userGroupDtoList.size());
        assertTrue(userGroupDtoListResult.containsAll(userGroupDtoList));
        verify(userGroupRepository, times(1)).findAll();
    }

    @Test
    void getById_whenUserGroupIsPresent_shouldReturnUserGroupDto() {
        var uuidA = UUID.randomUUID();
        var userGroupA = UserGroup.builder().uuid(uuidA).name("A").build();
        when(userGroupRepository.findById(uuidA)).thenReturn(Optional.of(userGroupA));

        var userGroupDtoA = new UserGroupDto();
        userGroupDtoA.setUuid(uuidA);
        userGroupDtoA.setName("A");
        when(userGroupDtoMapper.map(userGroupA)).thenReturn(userGroupDtoA);

        var userGroupDtoResult = userGroupService.getById(uuidA);

        assertEquals(userGroupDtoA, userGroupDtoResult);
        verify(userGroupRepository, times(1)).findById(any(UUID.class));
    }

    @Test
    void getById_whenUserGroupIsAbsent_shouldThrowException() {
        var uuidB = UUID.randomUUID();
        when(userGroupRepository.findById(uuidB)).thenThrow(new EntityNotFoundException());

        assertThrows(EntityNotFoundException.class, () -> userGroupService.getById(uuidB));
        verify(userGroupRepository, times(1)).findById(any(UUID.class));
    }

    @Test
    void create_shouldCreateUserGroupWithGeneratedUuid() {
        var uuidA = UUID.randomUUID();
        var userGroupDtoA = new UserGroupDto();
        userGroupDtoA.setUuid(uuidA);
        userGroupDtoA.setName("A");
        var userGroupA = UserGroup.builder().uuid(uuidA).name("A").build();

        var generatedUuid = UUID.randomUUID();
        var userGroupCreated = UserGroup.builder().uuid(generatedUuid).name("A").build();
        var userGroupDtoCreated = new UserGroupDto();
        userGroupDtoCreated.setUuid(generatedUuid);
        userGroupDtoCreated.setName("A");

        when(userGroupMapper.map(userGroupDtoA)).thenReturn(userGroupA);
        when(userGroupRepository.save(userGroupA)).thenReturn(userGroupCreated);
        when(userGroupDtoMapper.map(userGroupCreated)).thenReturn(userGroupDtoCreated);

        var userGroupDtoResult = userGroupService.create(userGroupDtoA);

        assertEquals(userGroupDtoCreated, userGroupDtoResult);
        verify(userGroupRepository, times(1)).save(any());
    }

    @Test
    void update_whenUserGroupIsPresent_shouldUpdateUserGroup() {
        var uuidA = UUID.randomUUID();
        var userGroupA = UserGroup.builder().uuid(uuidA).name("A").build();

        var uuidC = UUID.randomUUID();
        var userGroupDtoC = new UserGroupDto();
        userGroupDtoC.setUuid(uuidC);
        userGroupDtoC.setName("C");
        var userGroupC = UserGroup.builder().uuid(uuidC).name("C").build();

        var userGroupAUpdated = UserGroup.builder().uuid(uuidA).name("C").build();
        var userGroupDtoAUpdated = new UserGroupDto();
        userGroupDtoAUpdated.setUuid(uuidA);
        userGroupDtoAUpdated.setName("C");

        when(userGroupRepository.findById(uuidA)).thenReturn(Optional.of(userGroupA));
        when(userGroupMapper.map(userGroupDtoC)).thenReturn(userGroupC);
        when(userGroupRepository.save(any(UserGroup.class))).thenReturn(userGroupAUpdated);
        when(userGroupDtoMapper.map(userGroupAUpdated)).thenReturn(userGroupDtoAUpdated);

        var userGroupDtoResult = userGroupService.update(uuidA, userGroupDtoC);

        assertEquals(userGroupDtoAUpdated, userGroupDtoResult);
        verify(userGroupRepository, times(1)).findById(any(UUID.class));
        verify(userGroupRepository, times(1)).save(any(UserGroup.class));
    }

    @Test
    void update_whenUserGroupIsPresent_shouldThrowException() {
        var uuidB = UUID.randomUUID();
        var uuidC = UUID.randomUUID();
        var userGroupDtoC = new UserGroupDto();
        userGroupDtoC.setUuid(uuidC);
        userGroupDtoC.setName("C");
        when(userGroupRepository.findById(uuidB)).thenThrow(new EntityNotFoundException());

        assertThrows(EntityNotFoundException.class, () -> userGroupService.update(uuidB, userGroupDtoC));
        verify(userGroupRepository, times(1)).findById(any(UUID.class));
        verify(userGroupRepository, times(0)).save(any(UserGroup.class));
    }

    @Test
    void delete_whenUserGroupIsPresent_shouldDeleteUserGroup() {
        var uuidA = UUID.randomUUID();
        var userGroupA = UserGroup.builder().uuid(uuidA).name("A").build();
        doNothing().when(userGroupRepository).deleteById(uuidA);

        userGroupService.delete(uuidA);

        verify(userGroupRepository, times(1)).deleteById(any(UUID.class));
    }

    @Test
    void delete_whenUserGroupIsAbsent_shouldDoNothing() {
        var uuidA = UUID.randomUUID();
        var uuidB = UUID.randomUUID();
        var userGroupA = UserGroup.builder().uuid(uuidA).name("A").build();
        doNothing().when(userGroupRepository).deleteById(uuidB);

        userGroupService.delete(uuidB);

        verify(userGroupRepository, times(1)).deleteById(any(UUID.class));
    }
}