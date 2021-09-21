//package com.example.userservicetest.mapper;
//
//import com.example.userservicetest.entity.User;
//import com.example.userservicetest.entity.UserGroup;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import package_com.example.userservicetest.model.UserDto;
//
//import java.util.List;
//import java.util.Set;
//import java.util.UUID;
//
//@Mapper(componentModel = "spring", uses = {UserGroupMapper.class})
//public interface UserDtoMapper {
//
//    UserDto toDto(User user);
//
//    @Mapping(target = "uuid", ignore = true)
//    User toEntity(UserDto userDto);
//
//    List<UUID> map(Set<UserGroup> value);
//
//    Set<UserGroup> map(List<UUID> value);
//
//    UUID map(UserGroup value);
//
//    UserGroup map(UUID value);
//}
