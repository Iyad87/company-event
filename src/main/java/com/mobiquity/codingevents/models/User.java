package com.mobiquity.codingevents.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity // This tells Hibernate to make a table out of this class
public class User {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @GeneratedValue
    @Id
    private int id;
    @NotNull
    private String username;
    @NotNull
    private String pwHash;

    public User(String username, String password) {
        this.username = username;
        this.pwHash = encoder.encode(password);
    }

    // User objects should also be responsible for determining if a given password is a match for the hash stored by the object.

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }
}
