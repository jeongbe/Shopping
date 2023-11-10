package com.example.ShowpingProject.Controller;

import com.example.ShowpingProject.DTO.Login.Loginform;
import com.example.ShowpingProject.entity.Users;
import com.example.ShowpingProject.repository.UsersRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@Slf4j
public class LoginController {
    @Autowired
    UsersRepository usersRepository;


    @PostMapping("/logincheck")
    public String logincheck(Loginform form, HttpServletRequest request, RedirectAttributes rttr){
        HttpSession session = request.getSession();
        Users users = usersRepository.login(form.getLogin_ID(), form.getLogin_password());
        String fail = "아이디 혹은 비밀번호가 잘못 되었습니다";
        if(users != null){
            session.setAttribute("loginUser", users);
            log.info("User {} logged in successfully.", users.getUser_code());

            return "redirect:shopping/mainlogin";
        }
        else {
            rttr.addFlashAttribute("loginfail",fail);
            return "redirect:shopping/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        log.info("User logged out successfully.");
        return "redirect:shopping/main";
    }

}
