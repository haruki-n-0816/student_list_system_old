package com.example.student_list_system.users_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.student_list_system.users.UsersService;

@RestController
@RequestMapping("")
public class UsersControllerApi {

    @Autowired
    UsersService service;
    
    @GetMapping("/delete_complete")
    public void deleteCompleteUserApi(Integer id) {
        service.deleteUserPost(id);

    }
}
