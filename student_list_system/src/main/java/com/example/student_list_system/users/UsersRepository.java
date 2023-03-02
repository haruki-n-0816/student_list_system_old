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

    public List<Map<String, Object>> usersGet(Integer page){
        
        String query = "select * from users limit ?, 10;";
        List<Map<String, Object>> users = jdbcTemplate.queryForList(query, page * 10);
        
        return users;
    }

    public List<Map<String, Object>> usersCountGet(){

        String query = "select count(*) as 'count' from users;";
        List<Map<String, Object>> count = jdbcTemplate.queryForList(query);

        return count;
    }

    public boolean createUserRecord(Users users){

        String query = "insert into users(name, mail_address) values(?, ?);";
        jdbcTemplate.update(query, users.getName(), users.getMailAddress());

        return true;
    }
    public boolean deleteUserRecord(Users users){

        String query = "delete from users where id = ?;";
        jdbcTemplate.update(query, users.getId());

        return true;
    }

    public boolean updateUserRecord(Users users){

        String query = "update users set name = ?, mail_address = ? where id = ?;";
        jdbcTemplate.update(query, users.getName(), users.getMailAddress(), users.getId());
        
        return true;
    }
}
