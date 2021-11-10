package com.axel.userservice.mapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UserGroupMapperTest {

//    @InjectMocks
//    private final InvitationMapper mapper = spy(Mappers.getMapper(InvitationMapper.class));

//    UserGroupMapper userGroupMapper = Mockito.mock(UserGroupMapper.class);
    UserGroupMapper userGroupMapper = Mockito.spy(UserGroupMapper.class);

    @Test
    void map_shouldReturnUuid() {
//        var uuid = UUID.randomUUID();
//        var userGroup = UserGroup.builder().uuid(uuid).name("Group").build();
//
////        Mockito.when(userGroupMapper.map(userGroup)).thenCallRealMethod();
//
//        UUID uuidResult = userGroupMapper.mapToUuid(userGroup);
//
//        assertEquals(uuid, uuidResult);
    }
}