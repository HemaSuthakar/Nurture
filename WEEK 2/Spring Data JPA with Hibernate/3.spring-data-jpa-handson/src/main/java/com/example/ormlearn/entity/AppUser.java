package com.example.ormlearn.entity;

import jakarta.persistence.*;

// Named AppUser (table app_user) instead of User/user because USER is a
// reserved word in several databases (including H2 in some modes).
@Entity
@Table(name = "app_user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "AppUser{id=" + id + ", username='" + username + "'}";
    }
}
