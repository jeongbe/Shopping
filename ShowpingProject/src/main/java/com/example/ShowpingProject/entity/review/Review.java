package com.example.ShowpingProject.entity.review;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Review")
@Entity
public class Review {
    @Id
    @GeneratedValue
    int review_num;

    @JoinColumn(name = "order_num")
    @Column
    Long order_num;

    @JoinColumn(name = "prod_code")
    @Column
    Long prod_code;

    @JoinColumn(name = "user_code")
    @Column
    Long user_code;

    @JoinColumn
    @Column(name = "prod_size")
    String prod_size;

    @Column
    Date review_date;

    @Column
    String review_content;

    @Column
    String reviewimage_link;

}
