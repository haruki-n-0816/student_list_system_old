package com.example.student_list_system.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.student_list_system.model.Users;
import com.example.student_list_system.repository.UsersRepository;

@Service
public class UsersService {
    
    @Autowired
    private UsersRepository repository;

    public List<Users> getUsers(){
        List<Map<String, Object>> querySet = repository.usersAll();
        List<Users> users = new ArrayList<>();
        for (var record : querySet) {
            var user = new Users();
            user.setId((Integer)(record.get("id")));
            user.setName((String)record.get("name"));
            user.setMailAddress((String)record.get("mail_address"));
            users.add(user);
        }
        return users;
    }
}
