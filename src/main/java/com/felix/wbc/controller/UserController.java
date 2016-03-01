package com.felix.wbc.controller;

import com.felix.wbc.model.Users;
import com.felix.wbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by fsoewito on 2/20/2016.
 */

@Controller
@RequestMapping(value = "/user")
public class UserController extends GenericController<Users, Integer> {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        super(service);
        this.service = service;
    }

    @RequestMapping("/users")
    public Iterable<Users> getUsers() {
        return service.findAll();
    }


}
