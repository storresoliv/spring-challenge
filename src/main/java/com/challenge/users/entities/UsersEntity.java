package com.challenge.users.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z]).{5,}$",
            message = "Password must contain one upper char, at least two lower chars and at least two numbers")
    private String password;

    @OneToMany
    private List<PhonesEntity> phones;

    private String token;
    private Timestamp created;
    private Timestamp modified;
    private Timestamp lastLogin;
    private boolean isActive;
}
