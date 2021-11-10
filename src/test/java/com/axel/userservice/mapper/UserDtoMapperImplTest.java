package com.axel.userservice.mapper;

import com.axel.userservice.entity.User;
import com.axel.userservice.entity.UserGroup;
import org.junit.jupiter.api.BeforeEach;
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
class UserDtoMapperImplTest {

    private static final UUID USER_UUID = UUID.fromString("6694ae43-2f57-409f-8ce3-b3a94141af01");

    private static final UUID USER_GROUP_UUID = UUID.fromString("6694ae43-2f57-409f-8ce3-b3a94141af01");

    private User user;

    @Mock
    private User user2;

    private UserDto userDto;

    @Mock
    private UserGroup userGroup;

    @Mock
    private UserGroupDtoMapper userGroupDtoMapper;

    @InjectMocks
    private UserDtoMapperImpl testedEntry;

    @BeforeEach
    void init() {
        user = User.builder()
                .uuid(USER_UUID)
                .firstName("firstName")
                .lastName("lastName")
                .email("email")
                .userGroups(Set.of(userGroup))
                .build();

        userDto = new UserDto();
        userDto.setUuid(USER_UUID);
        userDto.setFirstName("firstName");
        userDto.setLastName("lastName");
        userDto.setEmail("email");
        userDto.setUserGroups(List.of(USER_GROUP_UUID));
    }

    @Test
    void map_shouldReturnUserDto() {
        doReturn(List.of(USER_GROUP_UUID)).when(userGroupDtoMapper).mapToUuidList(Set.of(userGroup));
        var result = testedEntry.map(user);
        assertThat(result).isEqualTo(userDto);
    }

    @Test
    void map_shouldReturnUserDto2() {
        doReturn(USER_UUID).when(user2).getUuid();
//        doReturn(List.of(USER_GROUP_UUID)).when(userGroupDtoMapper).mapToUuidList(Set.of(userGroup));
        var result = testedEntry.map(user2);
        assertThat(result.getUuid()).isEqualTo(USER_UUID);
    }

    @Test
    void map_whenUserIsNull_shouldReturnNull() {
        var result = testedEntry.map(null);
        assertThat(result).isNull();
    }

}
