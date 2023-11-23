package com.example.ShowpingProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "answer")
@Entity
public class Answer {

    //문의 답변 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long an_num;

    //문의 코드
    @JoinColumn(name = "qu_code")
    @Column
    private Long qu_code;

    //문의 답변 내용
    @Column
    private String an_content;

    //문의 답변 단 시간
    @Column
    LocalDate qu_date;
    @PrePersist
    public void setOrderdate(){
        this.qu_date = LocalDate.now();
    }



}
