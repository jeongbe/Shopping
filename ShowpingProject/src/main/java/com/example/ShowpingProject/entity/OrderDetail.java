package com.example.ShowpingProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orderdetail")
@Entity
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "prod_code")
    private Long prod_code;

    @JoinColumn(name = "user_code")
    private Long user_code;

    @JoinColumn(name = "order_id")
    private Long order_id;

    @Column
    private String prod_name;

    @Column
    private String prod_size;

    @Column
    private String prod_cnt;

    @Column
    private String prod_price;
}
