package com.axel.userservice.service.impl;

import com.axel.userservice.entity.User;
import com.axel.userservice.mapper.UserDtoMapper;
import com.axel.userservice.mapper.UserMapper;
import com.axel.userservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import package_com.example.userservicetest.model.UserDto;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private final static UUID USER_UUID = UUID.randomUUID();
    private final static UUID ANOTHER_USER_UUID = UUID.randomUUID();
    private final static UUID UUID_C = UUID.randomUUID();

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserDtoMapper userDtoMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDto userDtoA;

    @Mock
    private UserDto userDtoB;

    @Mock
    private User userA;

    @Mock
    private User userB;

    @Test
    void getAll_shouldReturnsList() {
        doReturn(List.of(userA, userB)).when(userRepository).findAll();
//        when(userRepository.findAll()).thenReturn(List.of(userA, userB));
        when(userDtoMapper.map(userA)).thenReturn(userDtoA);
        when(userDtoMapper.map(userB)).thenReturn(userDtoB);
        var result = userService.getAll();
        assertThat(result).hasSize(2).containsExactly(userDtoA, userDtoB);
    }

    private User getBuiltUser(UUID uuidA) {
        return User.builder().uuid(uuidA).build();
    }

    @Test
    void getById_whenUserIsPresent_shouldReturnUserDto() {
        doReturn(Optional.of(userA)).when(userRepository).findById(USER_UUID);
        doReturn(userDtoA).when(userDtoMapper).map(userA);
        var result = userService.getById(USER_UUID);
        assertThat(result).isEqualTo(userDtoA);
    }

    @Test
    void getById_whenUserIsAbsent_shouldThrowException() {
        doThrow(new EntityNotFoundException()).when(userRepository).findById(USER_UUID);
        assertThatThrownBy(() -> userService.getById(USER_UUID)).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void create_shouldCreateUserWithGeneratedUuid() {
        var userDtoA = new UserDto();
        userDtoA.setUuid(USER_UUID);
        var userA = getBuiltUser(USER_UUID);

        var generatedUuid = UUID.randomUUID();
        var userCreated = getBuiltUser(generatedUuid);
        var userDtoCreated = new UserDto();
        userDtoCreated.setUuid(generatedUuid);

        when(userMapper.map(userDtoA)).thenReturn(userA);
        when(userRepository.save(userA)).thenReturn(userCreated);
        when(userDtoMapper.map(userCreated)).thenReturn(userDtoCreated);

        var userDtoResult = userService.create(userDtoA);

        assertEquals(userDtoCreated, userDtoResult);
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void update_whenUserIsPresent_shouldUpdateUser() {
        var userA = getBuiltUser(USER_UUID);

        var userDtoC = new UserDto();
        userDtoC.setUuid(UUID_C);
        var userC = getBuiltUser(UUID_C);

        var userAUpdated = getBuiltUser(USER_UUID);
        var userDtoAUpdated = new UserDto();
        userDtoAUpdated.setUuid(USER_UUID);

        when(userRepository.findById(USER_UUID)).thenReturn(Optional.of(userA));
        when(userMapper.map(userDtoC)).thenReturn(userC);
        when(userRepository.save(any(User.class))).thenReturn(userAUpdated);
        when(userDtoMapper.map(userAUpdated)).thenReturn(userDtoAUpdated);

        var userDtoResult = userService.update(USER_UUID, userDtoC);

        assertEquals(userDtoAUpdated, userDtoResult);
        verify(userRepository, times(1)).findById(any(UUID.class));
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void update_whenUserIsPresent_shouldThrowException() {
        var userDtoC = new UserDto();
        userDtoC.setUuid(UUID_C);
        when(userRepository.findById(ANOTHER_USER_UUID)).thenThrow(new EntityNotFoundException());

        assertThrows(EntityNotFoundException.class, () -> userService.update(ANOTHER_USER_UUID, userDtoC));
        verify(userRepository, times(1)).findById(any(UUID.class));
        verify(userRepository, times(0)).save(any(User.class));
    }

    @Test
    void delete_whenUserIsPresent_shouldDeleteUser() {
        doNothing().when(userRepository).deleteById(USER_UUID);

        userService.delete(USER_UUID);

        verify(userRepository, times(1)).deleteById(any(UUID.class));
    }

    @Test
    void delete_whenUserIsAbsent_shouldDoNothing() {
        doNothing().when(userRepository).deleteById(ANOTHER_USER_UUID);

        userService.delete(ANOTHER_USER_UUID);

        verify(userRepository, times(1)).deleteById(any(UUID.class));
    }
}