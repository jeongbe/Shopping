package com.example.ShowpingProject.DTO;

import com.example.ShowpingProject.entity.Answer;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
@Getter
public class AnswerForm {
    //문의 답변 폼
    
    Long anNum;

    Long quCode;

    String anContent;

    LocalDate anDate;

    public Answer toEntity(){
        return new Answer(anNum,quCode,anContent,anDate);
    }
}
