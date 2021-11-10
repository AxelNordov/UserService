package com.axel.userservice.mapper;

import com.axel.userservice.entity.User;
import com.axel.userservice.entity.UserGroup;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import package_com.example.userservicetest.model.UserGroupDto;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class UserDtoMapperImplTest {

    private static final UUID USER_UUID = UUID.fromString("6694ae43-2f57-409f-8ce3-b3a94141af01");

    private static final UUID USER_GROUP_UUID = UUID.fromString("6694ae43-2f57-409f-8ce3-b3a94141af01");

    @Mock
    private User user;

    @Mock
    private UserGroup userGroup;

    @Mock
    private UserGroupDtoMapper userGroupDtoMapper;

    @InjectMocks
    private UserDtoMapperImpl testedEntry;


    @Test
    void map_shouldReturnUserDto() {
        doReturn(USER_UUID).when(user).getUuid();
        doReturn("FirstName").when(user).getFirstName();
        doReturn("LastName").when(user).getLastName();
        doReturn("Email").when(user).getEmail();
        doReturn(Set.of(userGroup)).when(user).getUserGroups();
        doReturn(List.of(USER_GROUP_UUID)).when(userGroupDtoMapper).mapToUuidList(Set.of(userGroup));
        var result = testedEntry.map(user);
        assertThat(result.getUuid()).isEqualTo(USER_UUID);
        assertThat(result.getFirstName()).isEqualTo("FirstName");
        assertThat(result.getLastName()).isEqualTo("LastName");
        assertThat(result.getEmail()).isEqualTo("Email");
        assertThat(result.getUserGroups()).isEqualTo(List.of(USER_GROUP_UUID));
    }

    @Test
    void map_whenUserIsNull_shouldReturnNull() {
        var result = testedEntry.map(null);
        assertThat(result).isNull();
    }

}
