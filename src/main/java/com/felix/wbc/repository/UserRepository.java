package com.felix.wbc.repository;

import com.felix.wbc.model.Users;
import org.springframework.stereotype.Repository;

/**
 * Created by fsoewito on 2/25/2016.
 */

@Repository
public interface UserRepository extends GenericRepository<Users, Integer> {
    Users findByUsername(String username);

    Users findByEmail(String email);
}
