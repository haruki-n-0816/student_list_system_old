package com.example.student_list_system.users;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("")
public class UsersController {

    @Autowired
    UsersService service;

    @GetMapping("/users")
    public String getUser(@RequestParam(defaultValue = "1", required = false) int page, Model model) {
        List<Users> users = service.getUsers(page - 1);
        model.addAttribute("users", users);

        long maxPage = service.countGet();
        model.addAttribute("maxPage", maxPage);
        model.addAttribute("page", page);

        return "users/users";
    }

    @GetMapping("/create")
    private String createUser() {

        return "users/create";
    }

    @PostMapping("/create_confirm")
    private String createConfirmUser(@RequestParam("userName") String name,
            @RequestParam("mailAddress") String mailAddress,
            @RequestPart("profileImage") MultipartFile profileImage, Model model) throws IOException {

        String base64 = Base64.getEncoder().encodeToString(profileImage.getBytes());

        model.addAttribute("confirmName", name);
        model.addAttribute("confirmMailAddress", mailAddress);
        model.addAttribute("confirmProfileImage", base64);

        return "users/create-confirm";
    }

    @PostMapping("/create_complete")
    public String createCompleteUser(@RequestParam("userName") String name,
            @RequestParam("mailAddress") String mailAddress,
            @RequestParam("profileImage") String profileImageString, Model model) throws IOException {

        byte[] profileImageDecoded = Base64.getDecoder().decode(profileImageString);
        
        BigInteger id = service.autoIncrementCountGet();
        
        String IdTimeFilename = id + "_" + DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS").format(LocalDateTime.now())
                + ".png";
                
        String filePath = "C:/Users/uxauser/road-to-geek/student_list_system/student_list_system/src/main/resources/static/student_list_system_profileImage/"
                + IdTimeFilename;
        String filePathDb = "/student_list_system_profileImage/" + IdTimeFilename;
        
        Files.write(Paths.get(filePath), profileImageDecoded);
        
        service.createUserPost(name, mailAddress, filePathDb);

        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Integer id, @RequestParam("userName") String name,
            @RequestParam("mailAddress") String mailAddress, Model model) {

        model.addAttribute("confirmId", id);
        model.addAttribute("confirmName", name);
        model.addAttribute("confirmMailAddress", mailAddress);

        return "users/delete";
    }

    @PostMapping("delete_complete")
    public String deleteCompleteUser(Integer id) {

        service.deleteUserPost(id);

        return "redirect:/users";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam("id") Integer id, @RequestParam("userName") String name,
            @RequestParam("mailAddress") String mailAddress, @RequestParam("profileImage") String profileImageString,Model model) {

        model.addAttribute("id", id);
        model.addAttribute("currentName", name);
        model.addAttribute("currentMailAddress", mailAddress);
        model.addAttribute("profileImagePath",profileImageString);

        return "users/update";
    }

    @PostMapping("/update_confirm")
    public String updateConfirmUser(@RequestParam("id") Integer id, @RequestParam("userName") String name,
            @RequestParam("mailAddress") String mailAddress,
            @RequestPart("profileImage") MultipartFile profileImage, Model model) throws IOException {

        String base64 = Base64.getEncoder().encodeToString(profileImage.getBytes());

        model.addAttribute("confirmId", id);
        model.addAttribute("confirmName", name);
        model.addAttribute("confirmMailAddress", mailAddress);
        model.addAttribute("confirmProfileImage", base64);

        return "users/update-confirm";
    }

    @PostMapping("/update_complete")
    public String updateCompleteUser(@RequestParam("id") Integer id, @RequestParam("userName") String name,
            @RequestParam("mailAddress") String mailAddress,
            @RequestParam("profileImage") String profileImageString, Model model) throws IOException {

        byte[] profileImageDecoded = Base64.getDecoder().decode(profileImageString);

        String IdTimeFilename = id + "_" + DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS").format(LocalDateTime.now())
                + ".png";

        String filePath = "C:/Users/uxauser/road-to-geek/student_list_system/student_list_system/src/main/resources/static/student_list_system_profileImage/"
                + IdTimeFilename;
        String filePathDb = "/student_list_system_profileImage/" + IdTimeFilename;

        Files.write(Paths.get(filePath), profileImageDecoded);
        
        service.updateUserPost(id, name, mailAddress,filePathDb);

        return "redirect:/users";
    }
}
