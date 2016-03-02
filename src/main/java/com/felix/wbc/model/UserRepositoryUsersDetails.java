package com.felix.wbc.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserRepositoryUsersDetails extends Users implements UserDetails {

    private static final long serialVersionUID = 1L;

    public UserRepositoryUsersDetails(Users users) {
        super(users);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return super.getRoles();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
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