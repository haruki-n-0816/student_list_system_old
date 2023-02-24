package com.example.student_list_system.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public boolean createUserPost(String name, String mail){

        Users user = new Users();
        user.setName(name);
        user.setMailAddress(mail);

        repository.createUserRecord(user);

        return true;
    }
}
