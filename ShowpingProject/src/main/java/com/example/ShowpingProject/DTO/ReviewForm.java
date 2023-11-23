package com.example.ShowpingProject.DTO;

import com.example.ShowpingProject.entity.review.Review;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import lombok.Data;

import java.util.Date;

@Data
public class ReviewForm {


    Long order_num;


    Long prod_code;


    Long user_code;


    String prod_size;


    Date review_date;


    String review_content;


    String reviewimage_link;


    public Review toEntity(Long order_num, Long prod_code, Long user_code, String prod_size){
        return new Review(null, order_num, prod_code, user_code, prod_size, null, review_content, reviewimage_link);
    }
}



