package com.example.projectprm392.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "account")
public class Account {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String Username;
    private String Password;
    private String Role;
    public Account() {
    }

    public Account(String username, String password) {
        Username = username;
        Password = password;
    }

    public Account(int id, String username, String password, String role) {
        this.id = id;
        Username = username;
        Password = password;
        Role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }
}
