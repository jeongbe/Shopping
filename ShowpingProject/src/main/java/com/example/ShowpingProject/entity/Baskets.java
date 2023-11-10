package com.example.ShowpingProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "basket")
@Entity

public class Baskets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JoinColumn(name = "user_code")
    @Column
    private Long user_code;

    @JoinColumn(name = "prod_code")
    @Column
    private Long prod_code;

    @Column(name = "prod_name")
    private String prod_name;

    @Column
    private String prod_size;

    @Column
    private String prod_cnt;

    //상품 낱개 가격
    @Column(name = "prod_discount_price")
    private String  prod_price;

    //전체 가격
    @Column
    private String total_price;
}
