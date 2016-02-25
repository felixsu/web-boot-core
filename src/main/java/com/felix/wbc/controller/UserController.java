package com.felix.wbc.controller;

import com.felix.wbc.model.User;
import com.felix.wbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by fsoewito on 2/20/2016.
 */

@Controller
@RequestMapping(value = "/person")
public class UserController extends GenericController<User, String> {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        super(service);
        this.service = service;
    }


}
