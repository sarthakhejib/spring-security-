package com.api.spring_security.controller;

import com.api.spring_security.model.SystemUser;
import com.api.spring_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public String register(@RequestBody SystemUser user){
        return service.verify(user);
       // return service.register(user);
    }

   /* @PostMapping("/login")
    public String login(@RequestBody SystemUser user){
        ;
    }*/


}
