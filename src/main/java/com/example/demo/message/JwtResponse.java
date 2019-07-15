package com.example.demo.message;

import org.springframework.security.core.GrantedAuthority;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;

public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private String username;
    private Long id;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtResponse(Long id, String username, String firstName, String lastName, String email, String accessToken, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
//        this.birthDate = birthDate;
        this.email = email;
        this.token = accessToken;
        this.authorities = authorities;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
