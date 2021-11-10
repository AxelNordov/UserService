package com.axel.userservice.mapper;

import com.axel.userservice.entity.UserGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import package_com.example.userservicetest.model.UserGroupDto;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class UserGroupMapperImplTest {

    private static final UUID USER_GROUP_UUID = UUID.fromString("6694ae43-2f57-409f-8ce3-b3a94141af01");

    private static final UUID UPDATE_USER_GROUP_UUID = UUID.fromString("6694ae43-2f57-409f-8ce3-b3a94141af02");

    private UserGroupDto userGroupDto;

    private UserGroup userGroup;

    @InjectMocks
    private UserGroupMapperImpl testedEntry;

    @BeforeEach
    void init() {
        userGroupDto = new UserGroupDto();
        userGroupDto.setUuid(USER_GROUP_UUID);
        userGroupDto.setName("name");

        userGroup = UserGroup.builder()
                .uuid(UPDATE_USER_GROUP_UUID)
                .name("name")
                .build();
    }

    @Test
    void map_shouldIgnoreUuid() {
        var result = testedEntry.map(userGroupDto);
        assertThat(result.getUuid()).isNull();
        assertThat(result.getName()).isEqualTo(userGroup.getName());
    }


    @Test
    void map_whenUserGroupDtoIsNull_shouldReturnNull() {
        var result = testedEntry.map((UUID)null); // TODO Yurii null overloading
        assertThat(result).isNull();
    }

    @Test
    void map_shouldUpdateUuid() { // TODO Yurii uuid ignore
        var result = testedEntry.map(userGroupDto, UPDATE_USER_GROUP_UUID);
        assertThat(result.getUuid()).isEqualTo(UPDATE_USER_GROUP_UUID);
        assertThat(result.getName()).isEqualTo(userGroup.getName());
    }

    @Test
    void map_whenUserGroupDtoIsNull_shouldReturnNull_2() {
        var result = testedEntry.map(null, UPDATE_USER_GROUP_UUID);
        assertThat(result).isNull();
    }

    @Test
    void map_whenUserGroupDtoAndUuidIsNull_shouldReturnNull() {
        var result = testedEntry.map(null, null);
        assertThat(result).isNull();
    }

    @Test
    void map_whenUuidIsNull_shouldIgnoreUuid() {
        var result = testedEntry.map(userGroupDto, null);
        assertThat(result.getUuid()).isNull();
        assertThat(result.getName()).isEqualTo(userGroup.getName());
    }

    @Test
    void map_shouldReturnUserGroup() {
        var result = testedEntry.map(USER_GROUP_UUID);
        assertThat(result.getUuid()).isEqualTo(USER_GROUP_UUID);
    }

    @Test
    void mapToUserGroupList_shouldReturnUserGroupList() {
        var result = testedEntry.mapToUserGroupList(List.of(USER_GROUP_UUID));
//        assertThat(result).hasSize(1).first()
//                .satisfies(userGroup -> assertThat(userGroup.getUuid()).isEqualTo(USER_GROUP_UUID));
//        assertThat(result.stream().findFirst().get().getUuid()).isEqualTo(USER_GROUP_UUID);
        assertThat(result).hasSize(1).first().hasFieldOrPropertyWithValue("uuid", USER_GROUP_UUID);
    }

    @Test
    void mapToUserGroupList_whenUuidIsNull_shouldReturnNull() {
        var result = testedEntry.mapToUserGroupList(null);
        assertThat(result).isNull();
    }

}
