package com.example.userservicetest.mapper;

import com.example.userservicetest.entity.User;
import org.mapstruct.*;
import package_com.example.userservicetest.model.UserDto;

//@Mapper(componentModel = "spring")
@Mapper(componentModel = "spring", uses = {UserGroupMapper.class})
public interface UserMapper {

    UserDto userToUserDto(User user);

    //    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
//            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    @Mapping(target = "uuid", ignore = true)
    User userDtoToUser(UserDto userDto);

}
