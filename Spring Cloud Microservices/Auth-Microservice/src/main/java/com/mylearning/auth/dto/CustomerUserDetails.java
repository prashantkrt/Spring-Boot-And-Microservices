package com.mylearning.auth.dto;

import com.mylearning.auth.entity.UserCredentials;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


public class CustomerUserDetails implements UserDetails {

    private final String username;
    private final String password;


    public CustomerUserDetails(UserCredentials userCredentials) {
        this.username = userCredentials.getUsername();
        this.password = userCredentials.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
