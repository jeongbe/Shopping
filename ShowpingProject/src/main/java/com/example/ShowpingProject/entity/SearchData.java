package com.example.ShowpingProject.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "searchdata")
@Entity
public class SearchData {


    @Id
    private String prod_name;

    @Column
    private String prod_discount_price;

    @Column
    private String image_link;

    @Column
    private String prod_code;

}
