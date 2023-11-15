package com.example.ShowpingProject.Controller;

import com.example.ShowpingProject.entity.Users;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MypageController {

    @GetMapping("/mypage/main/{user_code}")
    public String MypageController(HttpSession session, Model model){
        Users loginUser = (Users) session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);

        return "mypage/mypageMain";
    }

    @GetMapping("/mypage/review/list/{user_code}")
    public String Mypagereviewlist(HttpSession session, Model model){
        Users loginUser = (Users) session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);

        return "mypage/mypageReviewList";
    }

    @GetMapping("/mypage/like/{user_code}")
    public String Mypagelike(HttpSession session, Model model){
        Users loginUser = (Users) session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);

        return "mypage/mypageLike";
    }

    @GetMapping("/mypage/inquirylist/{user_code}")
    public String Mypageinquitylist(HttpSession session, Model model){
        Users loginUser = (Users) session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);

        return "mypage/mypageInquiryList";
    }
}















