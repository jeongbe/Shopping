package com.example.ShowpingProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "question")
@Entity
public class Question {

    //문의 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qu_code;

    //주분번호
    @JoinColumn(name = "order_id")
    @Column
    private Long order_id;

    //유저 코드
    @JoinColumn(name = "user_code")
    @Column
    private Long user_code;

    //상품 번호
    @JoinColumn(name = "prod_code")
    @Column
    private Long prod_code;

    //문의 타입
    @Column
    private String qu_type;

    //문의 제목
    @Column
    private String qu_title;

    //문의 내용
    @Column
    private String qu_content;

    //답변 여부
    @Column
    private String qu_answer;

    //문의 시간
    @Column
    @CreationTimestamp
    LocalDateTime qu_date_time;

    @Column
    LocalDate qu_date;
    @PrePersist
    public void setOrderdate(){
        this.qu_date = LocalDate.now();
    }

    //문의 이미지1
    @Column
    private String qu_image;

    //문의 이미지2
    @Column
    private String qu_image2;

    //문의 이미지3
    @Column
    private String qu_image3;

    @Column(name = "prod_name")
    private String prod_name;

    @Column(name = "user_id")
    private String user_id;

    @Column
    private String prod_discount_price;

}
