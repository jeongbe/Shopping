package com.example.ShowpingProject.Controller;

import com.example.ShowpingProject.DTO.AnswerForm;
import com.example.ShowpingProject.DTO.QuestionForm;
import com.example.ShowpingProject.entity.Answer;
import com.example.ShowpingProject.entity.Question;
import com.example.ShowpingProject.repository.AnswerRepository;
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
import java.util.Optional;

@Slf4j
@Controller
public class QuestionController {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    //관리자 1:1문의 리스트 페이지
    @GetMapping("/shopping/questionlist")
    public String questionlist(Model model){

        //전체 모든 회원 문의 목록들 관리자 페이지에 뿌려주기
        List<Question> AllQuestion = questionRepository.AllQuestion();
//        log.info(AllQuestion.toString());

        model.addAttribute("AllQuestion",AllQuestion);

        return "adminpage/questionlist";
    }

    //문의 목록 누르면 문의 번호 기준으로 문의 상세,답볍 페이지로 이동
    @GetMapping("/shopping/questionlist/answer/{qu_code}")
    public String Qanswer(@PathVariable("qu_code")  String QuCode, Model model, AnswerForm AForm){

        //1개의 문의 내역 가져옴
        Question getQ = questionRepository.oneQustion(QuCode);
//        log.info(getQ.toString());

        //모델로 만들어서 뷰에 뿌려줌
        model.addAttribute("getQ",getQ);
        
        //answer 폼에 있는 Qucode에 해당 Question의 Qucode를 넣어줌
        AForm.setQuCode(Long.valueOf(QuCode));
//        log.info(AForm.toString());

        if(AForm.getAnContent() != null){
            Answer answer = AForm.toEntity();
            getQ.setQu_answer("답변완료");
            questionRepository.save(getQ);
            Answer SaveAnswer = answerRepository.save(answer);
            log.info(SaveAnswer.toString());
            return "redirect:/shopping/questionlist";
        }else {
            log.info("문의 답변 비어있음");
        }

        return "adminpage/answer";
    }
}
