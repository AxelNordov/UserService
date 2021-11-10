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

@ExtendWith(MockitoExtension.class)
class UserMapperImplTest {

    private static final UUID USER_DTO_UUID = UUID.fromString("6694ae43-2f57-409f-8ce3-b3a94141af01");

    private static final UUID UPDATE_USER_UUID = UUID.fromString("6694ae43-2f57-409f-8ce3-b3a94141af02");

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

//    @Test
//    void map_shouldNotSetUuid() { // TODO
//        doReturn(USER_DTO_UUID).when(userDto).getUuid();
//        var result = testedEntry.map(userDto);
//        assertThat(result.getUuid()).isNull();
//    }

    @Test
    void map_whenUserDtoIsNull_shouldReturnNull() {
        var result = testedEntry.map(null);
        assertThat(result).isNull();
    }

    @Test
    void map_shouldUpdateUuid() {
//        doReturn(Set.of(userGroup)).when(userGroupMapper).mapToUserGroupList(List.of(USER_GROUP_UUID));
//        var result = testedEntry.map(userDto, UPDATE_USER_UUID);
//        assertThat(result.getUuid()).isEqualTo(UPDATE_USER_UUID);
//        assertThat(result.getFirstName()).isEqualTo(user.getFirstName());
//        assertThat(result.getLastName()).isEqualTo(user.getLastName());
//        assertThat(result.getEmail()).isEqualTo(user.getEmail());
//        assertThat(result.getUserGroups()).isEqualTo(user.getUserGroups());
    }

    @Test
    void map_whenUserDtoIsNull_shouldReturnNull_2() {
        var result = testedEntry.map(null, UPDATE_USER_UUID);
        assertThat(result).isNull();
    }

    @Test
    void map_whenUserDtoAndUuidIsNull_shouldReturnNull() {
        var result = testedEntry.map(null, null);
        assertThat(result).isNull();
    }

    @Test
    void map_whenUuidIsNull_shouldIgnoreUuid() { // TODO custom mapper
//        doReturn(Set.of(userGroup)).when(userGroupMapper).mapToUserGroupList(List.of(USER_GROUP_UUID));
//        var result = testedEntry.map(userDto, null);
//        assertThat(result.getUuid()).isNull();
//        assertThat(result.getFirstName()).isEqualTo(user.getFirstName());
//        assertThat(result.getLastName()).isEqualTo(user.getLastName());
//        assertThat(result.getEmail()).isEqualTo(user.getEmail());
//        assertThat(result.getUserGroups()).isEqualTo(user.getUserGroups());
    }

}