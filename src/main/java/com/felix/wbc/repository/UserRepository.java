package com.felix.wbc.repository;

import com.felix.wbc.model.User;

/**
 * Created by fsoewito on 2/25/2016.
 */
public interface UserRepository extends GenericRepository<User, String> {
    User findByUsername(String username);

    User findByEmail(String email);
}
