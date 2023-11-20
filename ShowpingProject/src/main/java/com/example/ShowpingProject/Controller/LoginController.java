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

    //로그인 시도 했을 때  매핑
    @PostMapping("/logincheck")
    public String logincheck(Loginform form, HttpServletRequest request, RedirectAttributes rttr, Model model){
        //유저가 입력한 정보가 레포지토리 login 매소드를 탐
        Users users = usersRepository.login(form.getLogin_ID(), form.getLogin_password());
        String fail = "아이디 혹은 비밀번호가 잘못 되었습니다";
        //쿼리문에서 select값이 not null인 경우 해당 아이디와 비밀번호가 존재하기때문에 로그인이 이루어짐
        if(users != null){
            //세션 처리하기
            HttpSession session = request.getSession();
           session.setAttribute("loginUser", users);
           session.getAttribute("loginUser");

            Users loginUser = (Users) session.getAttribute("loginUser");
            model.addAttribute("loginUser", loginUser);
            //세션 처리가 잘 되어있느지 확인하기 위한 로그
            log.info("User {} logged in successfully.", users.getUser_code());
            log.info(session.toString());
            //관리자용 로그인 구현
            if(users.getUser_code() == 38){
                return "adminpage/adminmain";
            }
            //유저용 로그인 구현
            else {
                return "redirect:shopping/mainlogin";
            }
        }
        //null값일 경우 해당 아이디봐 비밀번호가 없는것이기 때문에 아이또는 비밀번호를 확인하라는 문구가 뜸
        else {
            rttr.addFlashAttribute("loginfail",fail);
            return "redirect:shopping/login";
        }
    }

    //로그아웃시 세션을 만료시키는 매핑
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        //세션이 만료가 잘 되어있는지 확인하기 위한 로그
        log.info("User logged out successfully.");
        return "redirect:shopping/main";
    }

}
