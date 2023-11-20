package com.example.ShowpingProject.Controller;

import com.example.ShowpingProject.DTO.FindIDform;
import com.example.ShowpingProject.DTO.FindPWform;
import com.example.ShowpingProject.DTO.Joins.JoinsForm;
import com.example.ShowpingProject.entity.Users;
import com.example.ShowpingProject.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
@Slf4j
public class FindIdController {

    @Autowired
    UsersRepository usersRepository;

    @PostMapping("/find/ID")
    public String searchID(FindIDform form, Model model){
        //유저가 입력한 값을 매개변수로 DB에서 저장된 값이 있는지 확인하기 위한 과정
        String userID = usersRepository.finduserID(form.getFind_name(),form.getFind_phone());

        //입력한 정보가 없을 경우 오류메시지
        String error = "입력하신 정보가 맞는지 확인해주세요";

        //모델화 후 뷰에 뿌려주기
        if(userID != null){
        model.addAttribute("finduserID", userID);
        return "login/findID";
        }

        //정보가 하나라도 다를경우 반환페이지
        else {
            model.addAttribute("error", error);
            return "login/searchID";
        }

    }

    //비밀번호 찾기(변경) 유저의 정보가 일치하는지 확인하기 위한 매핑
    @PostMapping("/find/pw")
    public String searchpw(FindPWform form, Model model){

        //입력한 정보가 있는지 체크

        Long findpw = usersRepository.finduserpw(form.getPwfind_id(), form.getPwfind_phone());
        String error = "입력하신 정보가 맞는지 확인해주세요";


        //입력한 정보가 일치할경우 변경페이지로 반환
        if(findpw != null){
            //비밀번호 변경시 어떤 유저코드에 대한 변경인지 유저코드를 머스태치에 넘겨주기 위한 모델화
            model.addAttribute("findpw", findpw);
            //비밀번호 변경이 가능한 페이지 반환
            return "login/findpasw";
        }

        //정보가 하나라도 다를경우 반환페이지
        else {
            model.addAttribute("error", error);
            return "login/searchPasw";
        }

    }

    //비밀번호 변경하는 매핑
    @PostMapping("/change/pw/{user_code}")
    public String changepw(@PathVariable Long user_code, JoinsForm form){

        //유저코드에 해당하는 유저정보 가져오기
        Users users = usersRepository.findById(user_code).orElse(null);

        //유저 정보에 새로 변경할 비밀번호정보 넣어주기
        users.setUser_password(form.getUserpassword());

        //데이터가 잘 전달되었는지 확인
        log.info(users.toString());

        //변경된 데이터로 다시 저장하기
        usersRepository.save(users);

        return "login/login";
    }


}
