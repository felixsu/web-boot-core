package com.felix.wbc.service;

import com.felix.wbc.model.Users;
import com.felix.wbc.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by fsoewito on 2/26/2016.
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class.getName());

    @Autowired
    private UserRepository userRepository;

    public CustomUserDetailsService() {
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("Authenticating {}", username);
        Users users = userRepository.findByUsername(username);
        if (users == null) {
            LOGGER.error("Authenticating username {} return null", username);
            throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
        }
        return new UserRepositoryUsersDetails(users);
    }

    private final static class UserRepositoryUsersDetails extends Users implements UserDetails {

        private static final long serialVersionUID = 1L;

        private UserRepositoryUsersDetails(Users users) {
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
}
