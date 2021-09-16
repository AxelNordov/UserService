package com.example.userservicetest.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  private String uuid;

  private String firstName;

  private String lastName;

  private String email;

  @OneToMany
  private List<UserGroup> userGroups;

}

