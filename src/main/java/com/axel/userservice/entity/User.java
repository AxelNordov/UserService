package com.axel.userservice.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "uuid", updatable = false, nullable = false)
    @Type(type = "uuid-char")
    private UUID uuid;

    private String firstName;

    private String lastName;

    private String email;

    @ManyToMany
    @JoinTable(
            name = "user_user_groups",
            joinColumns = {@JoinColumn(name = "user_uuid")},
            inverseJoinColumns = {@JoinColumn(name = "user_groups_uuid")}
    )
    private Set<UserGroup> userGroups;

}
