package com.myLearning.service;

import com.myLearning.entity.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeUserDetails implements UserDetails {

    private String username;
    private String password;
    private List<GrantedAuthority> authorities;

    public EmployeeUserDetails(Employee employee) {
        username = employee.getUserName();
        password = employee.getPassword();
        authorities = Arrays.stream(employee.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                //.map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
