package com.example.student_list_system.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class TopPage {
    
    @RequestMapping("/")
    private String topPage(){
        return "top_page";
    }
}
