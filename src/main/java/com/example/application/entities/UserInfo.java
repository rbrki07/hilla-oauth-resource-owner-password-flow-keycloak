package com.example.application.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;

public class UserInfo {

    private String name;

    @JsonProperty("preferred_username")
    private String preferredUsername;

    @JsonProperty("given_name")
    private String givenName;

    @JsonProperty("family_name")
    private String familyName;

    private String email;

    private Collection<String> roles;

    public String getName() {
            return name;
        }

    public void setName(String name) {
            this.name = name;
        }

    public String getPreferredUsername() {
            return preferredUsername;
        }

    public void setPreferredUsername(String preferredUsername) {
            this.preferredUsername = preferredUsername;
        }

    public String getGivenName() {
            return givenName;
        }

    public void setGivenName(String givenName) {
            this.givenName = givenName;
        }

    public String getFamilyName() {
            return familyName;
        }

    public void setFamilyName(String familyName) {
            this.familyName = familyName;
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