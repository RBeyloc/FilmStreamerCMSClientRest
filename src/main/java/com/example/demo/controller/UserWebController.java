package com.example.demo.controller;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserWebController {

    @Autowired
    com.example.demo.service.UserService userService;

    @RequestMapping("/users")
    public String getWeb(Model containerToView) {
        containerToView.addAttribute("allUsers", userService.getAllUsers().get());
        return "showUsers";
    }

    @RequestMapping("/newUserForm")
    public String newUserForm(Model containerToView) {
        return "newUserForm";
    }

    @RequestMapping("/createUser")
    public String createUser(User user, Model containerToView) {
        userService.createUser(user);
        return "redirect:users";
    }

}
