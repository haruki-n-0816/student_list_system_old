package com.example.student_list_system.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("")
public class UsersController {
    
    @Autowired UsersService service;

    @GetMapping("/users")
    public String getUser(Model model) {
        List<Users> users = service.getUsers();
        model.addAttribute("users", users);

        return "users/users";
    }

    @GetMapping("/create")
    private String createUser(){
        return "users/create";
    }

    @PostMapping("/create_confirm")
    private String createConfirmUser(@RequestParam("userName") String name,@RequestParam("eMail") String eMail,Model model){
        model.addAttribute("confirmName", name);
        model.addAttribute("eMail", eMail);
        return "users/create-confirm";
    }

    @PostMapping("/create_complete")
    public String createCompleteUser(@RequestParam("userName") String name,@RequestParam("eMail") String eMail,Model model) {
        service.createUserPost(name, eMail);
        return "redirect:/users";
    }
}
