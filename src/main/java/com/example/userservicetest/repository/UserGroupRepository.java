package com.example.userservicetest.repository;

import com.example.userservicetest.entity.UserGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGroupRepository extends CrudRepository<UserGroup, String> {
    List<UserGroup> findAll();

    UserGroup findByUuid(String string);
}
