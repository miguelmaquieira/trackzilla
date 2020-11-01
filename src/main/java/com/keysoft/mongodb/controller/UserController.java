package com.keysoft.mongodb.controller;

import com.keysoft.mongodb.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/tza/users")
public class UserController {

    @RequestMapping(method = RequestMethod.PUT)
    public User addUserToSession(@RequestBody User user, HttpSession session) {
        session.setAttribute("USER", user);
        return user;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object getUserFromSession(HttpSession session){
        return session.getAttribute("USER");
    }
}
