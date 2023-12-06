package com.example.ShowpingProject.repository;

import com.example.ShowpingProject.entity.review.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {

    //상품코드와 작성한 리뷰의 상품코드가 일치할때 반환되는 쿼리
    @Query(value = "SELECT a.review_num, a.order_num, a.review_content, a.review_date ,a.reviewimage_link, a.user_code, a.prod_code,a.prod_size\n" +
            "FROM review a\n" +
            "INNER JOIN product b\n" +
            "ON a.prod_code = b.prod_code " +
            "WHERE a.prod_code = :id ", nativeQuery = true)
     ArrayList<Review> review(Long id);

    @Query(value = "SELECT A.*, b.order_date, c.prod_name, c.prod_discount_price \n" +
            "FROM Review A\n" +
            "INNER JOIN orderheader b\n" +
            "on a.order_num = b.order_id\n" +
            "INNER JOIN product c\n" +
            "on a.prod_code = c.prod_code\n" +
            "WHERE a.user_code = :usercode ", nativeQuery = true)
    ArrayList<Review> reviewlist(Long usercode);





}
