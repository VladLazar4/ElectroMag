package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

    @Id
    private Long id;

    private String username;
    private String password;
    private String name;
    private String type;

    public String getUser() {
        return "'" + getUsername() + "', '" + getPassword() + "', '" + getName() + "', '" + getType() + "'";
    }

    public User(String username, String password, String name, String type) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.type = type;
    }

    public User() {

    }

    public User(String username) {
        this.username = username;
    }
}
