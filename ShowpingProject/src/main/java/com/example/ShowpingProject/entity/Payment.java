package com.example.ShowpingProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment")
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pay_num;

    @JoinColumn(name = "order_id")
    private Long order_id;

    @JoinColumn(name = "user_code")
    private Long user_code;

    @Column
    private String tatal_price;

}

