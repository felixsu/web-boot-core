package com.felix.wbc.service;

import com.felix.wbc.model.User;
import com.felix.wbc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fsoewito on 2/20/2016.
 */

@Service
public class UserService extends GenericService<User, String> {

    @Autowired
    public UserService(UserRepository repository) {
        super(repository);
    }
}
