package com.example.demo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

import java.util.Collection;
import java.util.Collections;

@Data
public class Usuario implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private boolean status;

    public Usuario() {}

    public Usuario(Long id, String username, String password, boolean status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return status;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return status;
    }

    @Override
    public boolean isEnabled() {
        return status;
    }
}
