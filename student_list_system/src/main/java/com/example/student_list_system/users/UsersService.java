package com.example.student_list_system.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    
    @Autowired
    private UsersRepository repository;

    public List<Users> getUsers(Integer page){

        List<Map<String, Object>> querySet = repository.usersGet(page);
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

    public int countGet(){

        List<Map<String, Object>> querySet = repository.usersCountGet();
        Integer count = 0;
        List<Integer> userCount = new ArrayList<>();
        for(var record : querySet){
            // ここが怪しい
            userCount.add((Integer)(record.get("count")));
        }
        count = userCount.get(0);
        count = (count / 10) + 1;
        
        return count;
    }

    public boolean createUserPost(String name, String mail){

        Users user = new Users();
        user.setName(name);
        user.setMailAddress(mail);
        repository.createUserRecord(user);

        return true;
    }
    
    public boolean deleteUserPost(String id){

        Users user = new Users();
        user.setId(Integer.parseInt(id));
        repository.deleteUserRecord(user);

        return true;
    }

    public boolean updateUserPost(Integer id, String name, String mailAddress){

        Users user = new Users();
        user.setId(id);
        user.setName(name);
        user.setMailAddress(mailAddress);
        repository.updateUserRecord(user);

        return true;
    }
}
