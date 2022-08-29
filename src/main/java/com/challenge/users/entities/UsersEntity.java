package com.challenge.users.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Users")
@Builder
@Getter
@Setter
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @GenericGenerator(name= "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "VARCHAR(255)")
    private UUID id;

    @Column(unique = true)
    @Email
    private String email;
    @NotBlank
    private String name;
    @Pattern(regexp = "^([A-Z])+([a-z])+([0-9]{2})")
    private String password;
    @OneToMany
    private List<PhonesEntity> phones;
    private Timestamp created;
    private Timestamp modified;
    private Timestamp lastLogin;
    private String token;
    private boolean isActive;
}
