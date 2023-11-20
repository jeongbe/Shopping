package com.example.ShowpingProject.Controller;

import com.example.ShowpingProject.DTO.QuestionForm;
import com.example.ShowpingProject.entity.Question;
import com.example.ShowpingProject.repository.QuestionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
public class QuestionController {

    @Autowired
    QuestionRepository questionRepository;

    //관리자 1:1문의 리스트 페이지
    @GetMapping("/shopping/questionlist")
    public String questionlist(Model model){



        //전체 모든 회원 문의 목록들 관리자 페이지에 뿌려주기
        List<Question> AllQuestion = questionRepository.AllQuestion();
//        log.info(AllQuestion.toString());

        model.addAttribute("AllQuestion",AllQuestion);

        return "adminpage/questionlist";
    }
}
