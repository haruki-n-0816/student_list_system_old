package com.example.student_list_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.student_list_system.model.Users;
import com.example.student_list_system.service.UsersService;

@Controller
@RequestMapping("/users")
public class UsersController {
    
    @Autowired UsersService service;

    @GetMapping("")
    public String getUser(Model model) {
        List<Users> users = service.getUsers();
        model.addAttribute("users", users);

        return "users/index";
    }
}
