package com.example.ShowpingProject.DTO;

import com.example.ShowpingProject.entity.Question;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Getter
public class QuestionForm {

    //문의 번호
    private Long quCode;

    //상품 번호
    private Long prodCode;

    //주분번호
    private Long orderId;

    //유저 코드
    private Long userCode;

    //문의 타입
    private String quType;

    //문의 제목
    private String quTitle;

    //문의 내용
    private String quContent;

    //답변 여부
    private String quAnswer;

    //문의 시간
    LocalDateTime quDateTime;

    LocalDate quDate;

    //문의 이미지1
    private MultipartFile quImage;

    //문의 이미지2
    private MultipartFile quImage2;

    //문의 이미지3
    private MultipartFile quImage3;

    //상품 이름
    private String prodName;

    //회원 id
    private String userID;

    String prodPrice;

    public Question toEntity(){
        return new Question(quCode,prodCode,orderId,userCode,quType,quTitle,quContent,quAnswer,quDateTime,quDate,
                quImage != null ? quImage.getOriginalFilename() : null,
                quImage2 != null ? quImage2.getOriginalFilename() : null,
                quImage3 != null ? quImage3.getOriginalFilename() : null,
                prodName,userID,prodPrice);
    }
}
