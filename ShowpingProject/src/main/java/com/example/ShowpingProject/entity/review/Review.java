package com.example.ShowpingProject.entity.review;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Review")
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long review_num;

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

    //리뷰날짜
    @Column
    LocalDate review_date;
    @PrePersist
    public void setOrderdate(){
        this.review_date = LocalDate.now();
    }

    @Column
    String review_content;

    @Column
    String reviewimage_link;




}
