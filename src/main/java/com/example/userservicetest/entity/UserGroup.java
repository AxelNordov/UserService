package com.example.userservicetest.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserGroup {
    @Id
    private String uuid;

    private String name;


}
