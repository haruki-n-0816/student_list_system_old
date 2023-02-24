package com.example.student_list_system.users;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsersRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> usersAll(){
        
        String query = "select * from users;";
        List<Map<String, Object>> users = jdbcTemplate.queryForList(query);
        
        return users;
    }

    public boolean createUserRecord(Users users){
        String query = "insert into users(name, mail_address) values(?, ?)";
        jdbcTemplate.update(query, users.getName(), users.getMailAddress());

        return true;
    }
}
