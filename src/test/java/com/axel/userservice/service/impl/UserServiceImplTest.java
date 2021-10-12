package com.axel.userservice.service.impl;

import com.axel.userservice.entity.User;
import com.axel.userservice.mapper.UserDtoMapper;
import com.axel.userservice.mapper.UserMapper;
import com.axel.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import package_com.example.userservicetest.model.UserDto;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserMapper userMapper;

    @Mock
    UserDtoMapper userDtoMapper;

    @InjectMocks
    UserServiceImpl userService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll_shouldReturnsList() {
        var uuidA = UUID.randomUUID();
        var uuidB = UUID.randomUUID();
        var uuidC = UUID.randomUUID();
        var userA = User.builder().uuid(uuidA).firstName("A").build();
        var userB = User.builder().uuid(uuidB).firstName("B").build();
        var userC = User.builder().uuid(uuidC).firstName("C").build();
        var userList = List.of(userA, userB, userC);
        when(userRepository.findAll()).thenReturn(userList);

        var userDtoA = new UserDto();
        userDtoA.setUuid(uuidA);
        userDtoA.setFirstName("A");
        var userDtoB = new UserDto();
        userDtoB.setUuid(uuidB);
        userDtoB.setFirstName("B");
        var userDtoC = new UserDto();
        userDtoC.setUuid(uuidC);
        userDtoC.setFirstName("C");
        when(userDtoMapper.map(userA)).thenReturn(userDtoA);
        when(userDtoMapper.map(userB)).thenReturn(userDtoB);
        when(userDtoMapper.map(userC)).thenReturn(userDtoC);
        var userDtoList = List.of(userDtoA, userDtoB, userDtoC);

        var userDtoListResult = userService.getAll();

        assertEquals(3, userDtoList.size());
        assertTrue(userDtoListResult.containsAll(userDtoList));
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void getById_whenUserIsPresent_shouldReturnUserDto() {
        var uuidA = UUID.randomUUID();
        var userA = User.builder().uuid(uuidA).firstName("A").build();
        when(userRepository.findById(uuidA)).thenReturn(Optional.of(userA));

        var userDtoA = new UserDto();
        userDtoA.setUuid(uuidA);
        userDtoA.setFirstName("A");
        when(userDtoMapper.map(userA)).thenReturn(userDtoA);

        var userDtoResult = userService.getById(uuidA);

        assertEquals(userDtoA, userDtoResult);
        verify(userRepository, times(1)).findById(any(UUID.class));
    }

    @Test
    void getById_whenUserIsAbsent_shouldThrowException() {
        var uuidB = UUID.randomUUID();
        when(userRepository.findById(uuidB)).thenThrow(new EntityNotFoundException());

        assertThrows(EntityNotFoundException.class, () -> userService.getById(uuidB));
        verify(userRepository, times(1)).findById(any(UUID.class));
    }

    @Test
    void create_shouldCreateUserWithGeneratedUuid() {
        var uuidA = UUID.randomUUID();
        var userDtoA = new UserDto();
        userDtoA.setUuid(uuidA);
        userDtoA.setFirstName("A");
        var userA = User.builder().uuid(uuidA).firstName("A").build();

        var generatedUuid = UUID.randomUUID();
        var userCreated = User.builder().uuid(generatedUuid).firstName("A").build();
        var userDtoCreated = new UserDto();
        userDtoCreated.setUuid(generatedUuid);
        userDtoCreated.setFirstName("A");

        when(userMapper.map(userDtoA)).thenReturn(userA);
        when(userRepository.save(userA)).thenReturn(userCreated);
        when(userDtoMapper.map(userCreated)).thenReturn(userDtoCreated);

        var userDtoResult = userService.create(userDtoA);

        assertEquals(userDtoCreated, userDtoResult);
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void update_whenUserIsPresent_shouldUpdateUser() {
        var uuidA = UUID.randomUUID();
        var userA = User.builder().uuid(uuidA).firstName("A").build();

        var uuidC = UUID.randomUUID();
        var userDtoC = new UserDto();
        userDtoC.setUuid(uuidC);
        userDtoC.setFirstName("C");
        var userC = User.builder().uuid(uuidC).firstName("C").build();

        var userAUpdated = User.builder().uuid(uuidA).firstName("C").build();
        var userDtoAUpdated = new UserDto();
        userDtoAUpdated.setUuid(uuidA);
        userDtoAUpdated.setFirstName("C");

        when(userRepository.findById(uuidA)).thenReturn(Optional.of(userA));
        when(userMapper.map(userDtoC)).thenReturn(userC);
        when(userRepository.save(any(User.class))).thenReturn(userAUpdated);
        when(userDtoMapper.map(userAUpdated)).thenReturn(userDtoAUpdated);

        var userDtoResult = userService.update(uuidA, userDtoC);

        assertEquals(userDtoAUpdated, userDtoResult);
        verify(userRepository, times(1)).findById(any(UUID.class));
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void update_whenUserIsPresent_shouldThrowException() {
        var uuidB = UUID.randomUUID();
        var uuidC = UUID.randomUUID();
        var userDtoC = new UserDto();
        userDtoC.setUuid(uuidC);
        userDtoC.setFirstName("C");
        when(userRepository.findById(uuidB)).thenThrow(new EntityNotFoundException());

        assertThrows(EntityNotFoundException.class, () -> userService.update(uuidB, userDtoC));
        verify(userRepository, times(1)).findById(any(UUID.class));
        verify(userRepository, times(0)).save(any(User.class));
    }

    @Test
    void delete_whenUserIsPresent_shouldDeleteUser() {
        var uuidA = UUID.randomUUID();
        var userA = User.builder().uuid(uuidA).firstName("A").build();
        doNothing().when(userRepository).deleteById(uuidA);

        userService.delete(uuidA);

        verify(userRepository, times(1)).deleteById(any(UUID.class));
    }

    @Test
    void delete_whenUserIsAbsent_shouldDoNothing() {
        var uuidA = UUID.randomUUID();
        var uuidB = UUID.randomUUID();
        var userA = User.builder().uuid(uuidA).firstName("A").build();
        doNothing().when(userRepository).deleteById(uuidB);

        userService.delete(uuidB);

        verify(userRepository, times(1)).deleteById(any(UUID.class));
    }
}