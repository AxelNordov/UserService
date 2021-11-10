package com.axel.userservice.mapper;

import com.axel.userservice.entity.UserGroup;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import package_com.example.userservicetest.model.UserGroupDto;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class UserGroupMapperImplTest {

    private static final UUID USER_GROUP_UUID = UUID.fromString("6694ae43-2f57-409f-8ce3-b3a94141af01");

    private static final UUID UPDATE_USER_GROUP_UUID = UUID.fromString("6694ae43-2f57-409f-8ce3-b3a94141af02");

    @Mock
    private UserGroupDto userGroupDto;

    @InjectMocks
    private UserGroupMapperImpl testedEntry;

    @Test
    void map_shouldSetFields() {
        doReturn("Name").when(userGroupDto).getName();
        var result = testedEntry.map(userGroupDto);
        assertThat(result.getName()).isEqualTo("Name");
    }

    @Test
    void map_shouldNotSetUuid() {
        lenient().doReturn(USER_GROUP_UUID).when(userGroupDto).getUuid();
        var result = testedEntry.map(userGroupDto);
        assertThat(result.getUuid()).isNull();
    }

    @Test
    void map_whenUserGroupDtoIsNull_shouldReturnNull() {
        var result = testedEntry.map((UserGroupDto) null);
        assertThat(result).isNull();
    }

    @Test
    void mapWithUuid_shouldSetFields() {
        doReturn("Name").when(userGroupDto).getName();
        var result = testedEntry.map(userGroupDto, UPDATE_USER_GROUP_UUID);
        assertThat(result.getName()).isEqualTo("Name");
    }

    @Test
    void mapWithUuid_shouldSetUuid() {
        lenient().doReturn(USER_GROUP_UUID).when(userGroupDto).getUuid();
        var result = testedEntry.map(userGroupDto, UPDATE_USER_GROUP_UUID);
        assertThat(result.getUuid()).isEqualTo(UPDATE_USER_GROUP_UUID);
    }

    @Test
    void mapWithUuid_whenUserGroupDtoAndUuidIsNull_shouldReturnNull() {
        var result = testedEntry.map(null, null);
        assertThat(result).isNull();
    }

    @Test
    void mapUuid_shouldReturnUserGroup() {
        var result = testedEntry.map(USER_GROUP_UUID);
        assertThat(result.getUuid()).isEqualTo(USER_GROUP_UUID);
    }

    @Test
    void mapUuid_whenUuidIsNull_shouldReturnNull() {
        var result = testedEntry.map((UUID) null);
        assertThat(result).isNull();
    }

    @Test
    void mapToUserGroupList_shouldReturnUserGroupList() {
        var result = testedEntry.mapToUserGroupList(List.of(USER_GROUP_UUID));
        assertThat(result)
                .hasSize(1)
                .first().satisfies(userGroup -> assertThat(userGroup.getUuid()).isEqualTo(USER_GROUP_UUID));
    }

    @Test
    void mapToUserGroupList_whenUuidIsNull_shouldReturnNull() {
        var result = testedEntry.mapToUserGroupList(null);
        assertThat(result).isNull();
    }

}
