package com.example.ShowpingProject.Controller;

import com.example.ShowpingProject.DTO.FindIDform;
import com.example.ShowpingProject.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FindIdController {

    @Autowired
    UsersRepository usersRepository;

    @PostMapping("/find/ID")
    public String searchID(FindIDform form, Model model){
        //유저가 입력한 값을 매개변수로 DB에서 저장된 값이 있는지 확인하기 위한 과정
        String userID = usersRepository.finduserID(form.getFind_name(),form.getFind_phone());
        //모델화 후 뷰에 뿌려주기
        model.addAttribute("finduserID", userID);

        return "login/findID";

    }
}
