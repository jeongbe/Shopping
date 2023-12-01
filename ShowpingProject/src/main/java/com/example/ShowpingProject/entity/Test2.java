package com.example.ShowpingProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orderdetailtest")
@Entity
public class Test2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long prod_code;

    @Column
    private Long user_code;

    @Column
    private Long order_id;

    @Column
    private String prod_name;

    @Column
    private String prod_size;

    @Column
    private String prod_cnt;

    @Column
    private String prod_price;

    @Column
    private String image_link;
}
