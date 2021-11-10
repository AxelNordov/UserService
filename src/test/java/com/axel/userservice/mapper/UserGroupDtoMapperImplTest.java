package com.axel.userservice.mapper;

import com.axel.userservice.entity.UserGroup;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class UserGroupDtoMapperImplTest {

    private static final UUID USER_GROUP_UUID = UUID.fromString("6694ae43-2f57-409f-8ce3-b3a94141af01");

    @Mock
    private UserGroup userGroup;

    @InjectMocks
    private UserGroupDtoMapperImpl testedEntry;

    @Test
    void map_shouldSetFields() {
        doReturn("Name").when(userGroup).getName();
        var result = testedEntry.map(userGroup);
        assertThat(result.getName()).isEqualTo("Name");
    }

    @Test
    void map_shouldSetUuid() {
        lenient().doReturn(USER_GROUP_UUID).when(userGroup).getUuid();
        var result = testedEntry.map(userGroup);
        assertThat(result.getUuid()).isEqualTo(USER_GROUP_UUID);
    }

    @Test
    void map_whenUserGroupDtoIsNull_shouldReturnNull() {
        var result = testedEntry.map(null);
        assertThat(result).isNull();
    }

    @Test
    void mapToUuid_shouldReturnUuid() {
        doReturn(USER_GROUP_UUID).when(userGroup).getUuid();
        var result = testedEntry.mapToUuid(userGroup);
        assertThat(result).isEqualTo(USER_GROUP_UUID);
    }

    @Test
    void mapToUuid_whenUserGroupIsNull_shouldReturnNull() {
        var result = testedEntry.mapToUuid(null);
        assertThat(result).isNull();
    }

    @Test
    void mapToUuidList_shouldReturnUuidList() {
        doReturn(USER_GROUP_UUID).when(userGroup).getUuid();
        var result = testedEntry.mapToUuidList(List.of(userGroup));
        assertThat(result)
                .hasSize(1)
                .first().satisfies(uuid -> assertThat(uuid).isEqualTo(USER_GROUP_UUID));
    }

    @Test
    void mapToUuidList_whenListIsNull_shouldReturnNull() {
        var result = testedEntry.mapToUuidList(null);
        assertThat(result).isNull();
    }

}
