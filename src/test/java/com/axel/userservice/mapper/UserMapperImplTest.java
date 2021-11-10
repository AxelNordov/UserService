package com.axel.userservice.mapper;

import com.axel.userservice.entity.UserGroup;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import package_com.example.userservicetest.model.UserDto;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class UserMapperImplTest {

    private static final UUID USER_UUID = UUID.fromString("6694ae43-2f57-409f-8ce3-b3a94141af01");

    private static final UUID UPDATE_USER_UUID = UUID.fromString("98c57d11-8e5d-4423-b0be-caaf4c4755c0");

    private static final UUID USER_GROUP_UUID = UUID.fromString("6694ae43-2f57-409f-8ce3-b3a94141af01");

    @Mock
    private UserDto userDto;

    @Mock
    private UserGroup userGroup;

    @Mock
    private UserGroupMapper userGroupMapper;

    @InjectMocks
    private UserMapperImpl testedEntry;

    @Test
    void map_shouldSetFields() {
        doReturn("FirstName").when(userDto).getFirstName();
        doReturn("LastName").when(userDto).getLastName();
        doReturn("Email").when(userDto).getEmail();
        doReturn(List.of(USER_GROUP_UUID)).when(userDto).getUserGroups();
        doReturn(Set.of(userGroup)).when(userGroupMapper).mapToUserGroupList(List.of(USER_GROUP_UUID));
        var result = testedEntry.map(userDto);
        assertThat(result.getFirstName()).isEqualTo("FirstName");
        assertThat(result.getLastName()).isEqualTo("LastName");
        assertThat(result.getEmail()).isEqualTo("Email");
        assertThat(result.getUserGroups()).isEqualTo(Set.of(userGroup));
    }

    @Test
    void map_shouldNotSetUuid() {
        lenient().doReturn(USER_UUID).when(userDto).getUuid();
        var result = testedEntry.map(userDto);
        assertThat(result.getUuid()).isNull();
    }

    @Test
    void map_whenUserDtoIsNull_shouldReturnNull() {
        var result = testedEntry.map(null);
        assertThat(result).isNull();
    }

    @Test
    void mapWithUuid_shouldSetFields() {
        doReturn("FirstName").when(userDto).getFirstName();
        doReturn("LastName").when(userDto).getLastName();
        doReturn("Email").when(userDto).getEmail();
        doReturn(List.of(USER_GROUP_UUID)).when(userDto).getUserGroups();
        doReturn(Set.of(userGroup)).when(userGroupMapper).mapToUserGroupList(List.of(USER_GROUP_UUID));
        var result = testedEntry.map(userDto, UPDATE_USER_UUID);
        assertThat(result.getFirstName()).isEqualTo("FirstName");
        assertThat(result.getLastName()).isEqualTo("LastName");
        assertThat(result.getEmail()).isEqualTo("Email");
        assertThat(result.getUserGroups()).isEqualTo(Set.of(userGroup));
    }

    @Test
    void mapWithUuid_shouldNotSetUuid() {
        lenient().doReturn(USER_UUID).when(userDto).getUuid();
        var result = testedEntry.map(userDto, UPDATE_USER_UUID);
        assertThat(result.getUuid()).isEqualTo(UPDATE_USER_UUID);
    }

    @Test
    void mapWithUuid_whenUserDtoAndUuidAreNull_shouldReturnNull() {
        var result = testedEntry.map(null, null);
        assertThat(result).isNull();
    }

}
