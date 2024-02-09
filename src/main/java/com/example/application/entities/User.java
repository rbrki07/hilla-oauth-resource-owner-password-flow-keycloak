package com.example.application.entities;

import dev.hilla.Nonnull;
import java.util.Collection;

public class User {

    @Nonnull
    private String username;

    @Nonnull
    private String firstname;

    @Nonnull
    private String lastname;

    @Nonnull
    private String email;

    @Nonnull
    private Collection<String> roles;

    public User(UserInfo userInfo) {
        this.username = userInfo.getPreferredUsername();
        this.firstname = userInfo.getGivenName();
        this.lastname = userInfo.getFamilyName();
        this.email = userInfo.getEmail();
        this.roles = userInfo.getRoles();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<String> getRoles() {
        return roles;
    }

    public void setRoles(Collection<String> roles) {
        this.roles = roles;
    }
}
