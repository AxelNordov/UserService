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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private final static UUID USER_UUID = UUID.fromString("6694ae43-2f57-409f-8ce3-b3a94141af01");

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserDtoMapper userDtoMapper;

    @Mock
    private UserDto userDto;

    @Mock
    private UserDto anotherUserDto;

    @Mock
    private UserDto persistedUserDto;

    @Mock
    private User user;

    @Mock
    private User existedUser;

    @Mock
    private User anotherUser;

    @Mock
    private User persistedUser;

    @InjectMocks
    private UserServiceImpl testedEntry;

    @Test
    void getAll_shouldReturnsList() {
        doReturn(List.of(user, anotherUser)).when(userRepository).findAll();
        when(userDtoMapper.map(user)).thenReturn(userDto);
        when(userDtoMapper.map(anotherUser)).thenReturn(anotherUserDto);
        var result = testedEntry.getAll();
        assertThat(result).hasSize(2).containsExactly(userDto, anotherUserDto);
    }

    @Test
    void getById_whenUserIsPresent_shouldReturnUserDto() {
        doReturn(Optional.of(existedUser)).when(userRepository).findById(USER_UUID);
        doReturn(userDto).when(userDtoMapper).map(existedUser);
        var result = testedEntry.getById(USER_UUID);
        assertThat(result).isEqualTo(userDto);
    }

    @Test
    void getById_whenUserIsAbsent_shouldThrowException() {
        doReturn(Optional.empty()).when(userRepository).findById(USER_UUID);
        assertThatThrownBy(() -> testedEntry.getById(USER_UUID))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("there is no user with id: 6694ae43-2f57-409f-8ce3-b3a94141af01");
    }

    @Test
    void create_shouldCreateUserWithGeneratedUuid() {
        doReturn(user).when(userMapper).map(userDto);
        doReturn(persistedUser).when(userRepository).save(user);
        doReturn(persistedUserDto).when(userDtoMapper).map(persistedUser);
        var result = testedEntry.create(userDto);
        assertThat(result).isEqualTo(persistedUserDto);
    }

    @Test
    void update_whenUserIsPresent_shouldUpdateUser() {
        doReturn(Optional.of(existedUser)).when(userRepository).findById(USER_UUID);
        doReturn(user).when(userMapper).map(anotherUserDto, USER_UUID);
        doReturn(persistedUser).when(userRepository).save(user);
        doReturn(persistedUserDto).when(userDtoMapper).map(persistedUser);
        var result = testedEntry.update(USER_UUID, anotherUserDto);
        assertThat(result).isEqualTo(persistedUserDto);
    }

    @Test
    void update_whenUserIsPresent_shouldThrowException() {
        doReturn(Optional.empty()).when(userRepository).findById(USER_UUID);
        assertThatThrownBy(() -> testedEntry.update(USER_UUID, anotherUserDto))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("there is no user with id: 6694ae43-2f57-409f-8ce3-b3a94141af01");
    }

    @Test
    void delete_whenUserIsPresent_shouldDeleteUser() {
        testedEntry.delete(USER_UUID);
        verify(userRepository).deleteById(USER_UUID);
    }
}
