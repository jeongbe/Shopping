package com.example.ShowpingProject.Controller;

import com.example.ShowpingProject.DTO.Joins.JoinsForm;
import com.example.ShowpingProject.DTO.Usercheckform;
import com.example.ShowpingProject.DTO.Userupdate;
import com.example.ShowpingProject.entity.Users;
import com.example.ShowpingProject.repository.UsersRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
@Slf4j
public class UserinfoController {

    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/mypage/info/{user_code}")
    public String userinfo(HttpSession session, Model model){
        Users loginUser = (Users) session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);

        return "mypage/mypageInfoCheck";
    }

    @PostMapping("/userinfoCheck/{user_code}")
    public String userinfocheck(HttpSession session, Model model, Usercheckform form, @PathVariable Long user_code){

        Users loginUser = (Users) session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);



        Long usercode = usersRepository.userinfocheck(user_code, form.getPassword());

        if (usercode != null){
            return "mypage/mypageInfo";
        }
        else {

            String error = "비밀번호를 다시 확인하세요";
            model.addAttribute("error", error);

            return "mypage/mypageInfoCheck";
        }




    }

    @PostMapping("/userinfo/update/{user_code}")
    public String userinfoupdate(@PathVariable Long user_code, Userupdate update,HttpSession session, Model model){

        Users loginUser = (Users) session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);


        //업데이트 될 대상을 유저코드로 가져오기
        Users userstarget = usersRepository.findById(user_code).orElse(null);

        log.info(userstarget.toString());

        //바뀐 유저 정보들을 다시 세팅해주기
        if ((!Objects.equals(userstarget.getUser_email(), update.getMypage_email())) && (update.getMypage_email() != "")){
            userstarget.setUser_email(update.getMypage_email());
            log.info(userstarget.toString());
        }

        if ((!Objects.equals(userstarget.getUser_phone(), update.getMypage_number())) && (update.getMypage_number() != "")){
            userstarget.setUser_phone(update.getMypage_number());
        }

        if ((!Objects.equals(userstarget.getUser_addr(), update.getMypage_address())) && (update.getMypage_address() != "")){
            userstarget.setUser_addr(update.getMypage_address());
        }

        if ((!Objects.equals(userstarget.getUser_detail_addr(), update.getMypage_address_detail())) && (update.getMypage_address_detail() != "")){
            userstarget.setUser_detail_addr(update.getMypage_address_detail());
        }

        if ((!Objects.equals(userstarget.getUser_password(), update.getMypage_password())) && (update.getMypage_password() != "")){
            userstarget.setUser_password(update.getMypage_password());
        }


        usersRepository.save(userstarget);

        return "redirect:/mypage/main/" + user_code;
    }

}
