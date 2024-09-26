package com.sundar.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sundar.model.UserInfo;

public class UserInfoUserDetails implements UserDetails {
    private final UserInfo userInfo;

    public UserInfoUserDetails(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return user authorities (implement as needed)
        return null; // Replace with actual authorities
    }

    @Override
    public String getPassword() {
        return userInfo.getPwd(); // Adjust based on your UserInfo model
    }

    @Override
    public String getUsername() {
        return userInfo.getUname(); // Adjust based on your UserInfo model
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Implement your logic
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Implement your logic
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Implement your logic
    }

    @Override
    public boolean isEnabled() {
        return true; // Implement your logic
    }
}

