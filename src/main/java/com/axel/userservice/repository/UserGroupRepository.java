package com.axel.userservice.repository;

import com.axel.userservice.entity.UserGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserGroupRepository extends CrudRepository<UserGroup, UUID> {

    List<UserGroup> findAll();

}
