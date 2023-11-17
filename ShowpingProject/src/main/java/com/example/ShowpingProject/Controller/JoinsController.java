package com.example.ShowpingProject.Controller;


import com.example.ShowpingProject.DTO.Joins.JoinsForm;
import com.example.ShowpingProject.entity.Users;
import com.example.ShowpingProject.repository.joins.JoinsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class JoinsController {
    @Autowired
    private JoinsRepository joinsRepository;

    @PostMapping("/joins/create")
    public String createJoins(JoinsForm form) {    //폼 데이터를 DTO로 받기
        System.out.println(form.toString());    //오류 발생했을때 찾기편하라고 만듦
        //1. DTO를 엔티티로 변환
        Users users_save = form.toEntity();
        System.out.println(users_save.toString());
        //2. 리파지터리로 엔티티를 DB에 저장
        Users saved = joinsRepository.save(users_save);
        System.out.println(saved.toString());
        return "login/login";
    }



    @PostMapping("/joins/idcheck")
    @ResponseBody  // 이 애노테이션을 추가하여 HTTP 응답 본문이 JSON이 될 것임을 명시합니다.
    public ResponseEntity<?> validateUserId(@RequestParam String userid) {
        log.info(userid);
        Users isAvailable = joinsRepository.findByUserId(userid);
        Map<String, Users> response = new HashMap<>();
        response.put("isAvailable", isAvailable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
