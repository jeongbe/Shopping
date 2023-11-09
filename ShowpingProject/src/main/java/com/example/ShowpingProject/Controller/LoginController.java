package com.example.ShowpingProject.Controller;

import com.example.ShowpingProject.DTO.Login.Loginform;
import com.example.ShowpingProject.entity.Users;
import com.example.ShowpingProject.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@Slf4j
public class LoginController {
    @Autowired
    UsersRepository usersRepository;

    @PostMapping("/logincheck")
    public String logincheck(Loginform form){
        Users users = usersRepository.login(form.getLogin_ID(), form.getLogin_password());
        if(users != null){
            return "main";
        }
        else {
            return "login/login";
        }
    }
}
