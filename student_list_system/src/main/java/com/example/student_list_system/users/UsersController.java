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
    public String getUser(@RequestParam(defaultValue = "1", required = false) int page, Model model) {
        List<Users> users = service.getUsers(page - 1);
        model.addAttribute("users", users);

        long maxPage = service.countGet();
        model.addAttribute("maxPage", maxPage);
        
        return "users/users";
    }

    @GetMapping("/create")
    private String createUser(){

        return "users/create";
    }

    @PostMapping("/create_confirm")
    private String createConfirmUser(@RequestParam("userName") String name,@RequestParam("mailAddress") String mailAddress,Model model){

        model.addAttribute("confirmName", name);
        model.addAttribute("confirmMailAddress", mailAddress);

        return "users/create-confirm";
    }

    @PostMapping("/create_complete")
    public String createCompleteUser(@RequestParam("userName") String name,@RequestParam("mailAddress") String mailAddress,Model model) {

        service.createUserPost(name, mailAddress);

        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Integer id,@RequestParam("userName") String name,@RequestParam("mailAddress") String mailAddress, Model model){

        model.addAttribute("confirmId",id);
        model.addAttribute("confirmName",name);
        model.addAttribute("confirmMailAddress", mailAddress);

        return "users/delete";
    }
    @PostMapping("delete_complete")
    public String deleteCompleteUser(@RequestParam("id") String id,Model model){
        
        service.deleteUserPost(id);

        return "redirect:/users";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam("id") Integer id,@RequestParam("userName") String name,@RequestParam("mailAddress") String mailAddress, Model model){
        
        model.addAttribute("id",id);
        model.addAttribute("currentName",name);
        model.addAttribute("currentMailAddress", mailAddress);

        return "users/update";
    }

    @PostMapping("/update_confirm")
    public String updateConfirmUser(@RequestParam("id") Integer id,@RequestParam("userName") String name,@RequestParam("mailAddress") String mailAddress, Model model){

        model.addAttribute("confirmId",id);
        model.addAttribute("confirmName",name);
        model.addAttribute("confirmMailAddress", mailAddress);

        return "users/update-confirm";
    }

    @PostMapping("/update_complete")
    public String updateCompleteUser(@RequestParam("id") Integer id,@RequestParam("userName") String name,@RequestParam("mailAddress") String mailAddress, Model model){
        
        service.updateUserPost(id, name, mailAddress);

        return "redirect:/users";
    }
}
