package com.example.ShowpingProject.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orderheader")
@Entity
public class OrderHeader {

    //상품 주문 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @SequenceGenerator(name = "ORDER_HEADER", initialValue = 100)
    private Long order_id;
//    , generator = "ORDER_HEADER"

    //유저 코드 조인
    @JoinColumn(name = "user_code")
    @Column
    private Long userCode;

    //주문 날짜
    @Column(name = "order_date")
    LocalDate order_date;
    @PrePersist
    public void setOrderdate(){
        this.order_date = LocalDate.now();
    }

    //주문 시간
    @Column
    @CreationTimestamp
    LocalDateTime order_date_time;


    //구매 총 가격
    @Column
    String total_price;

    @Column(name = "user_name")
    String user_name;

    @Column(name = "user_detail_addr")
    String user_detail_addr;

    @Column(name = "user_detail_addr2", nullable = true)
    String user_detail_addr2;

    @Column(name = "user_detail_addr3", nullable = true)
    String user_detail_addr3;

    @Column(name = "user_addr")
    String user_addr;

    @Column(name = "user_addr2", nullable = true)
    String user_addr2;

    @Column(name = "user_addr3", nullable = true)
    String user_addr3;



}
