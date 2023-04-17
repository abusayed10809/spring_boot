package com.example.codejava.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String viewHomePage(){
        return "index";
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model){
        model.addAttribute("user_entity", new UserEntity());
        return "signup_form";
    }

    @PostMapping("/process_registration")
    public String processRegistration(UserEntity userEntity){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(encodedPassword);
        userRepository.save(userEntity);
        return "registration_success";
    }

    @GetMapping("/list_users")
    public String viewUsersList(Model model){
        List<UserEntity> userEntityList = userRepository.findAll();
        model.addAttribute("userEntityList", userEntityList);
        return "users";
    }
}
