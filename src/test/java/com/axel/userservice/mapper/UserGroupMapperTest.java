package com.axel.userservice.mapper;

import com.axel.userservice.entity.UserGroup;
import com.axel.userservice.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserGroupMapperTest {

    UserGroupMapper userGroupMapper = Mockito.mock(UserGroupMapper.class);

    @Test
    void map_shouldReturnUuid() {
        var uuid = UUID.randomUUID();
        var userGroup = UserGroup.builder().uuid(uuid).name("Group").build();

        Mockito.when(userGroupMapper.map(userGroup)).thenCallRealMethod();

        UUID uuidResult = userGroupMapper.map(userGroup);

        assertEquals(uuid, uuidResult);
    }
}