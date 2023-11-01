package com.example.ShowpingProject.repository;

import com.example.ShowpingProject.entity.Baskets;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface BasketRepository extends CrudRepository<Baskets, Long> {


    @Query(value = "SELECT id,prod_cnt, basket.prod_code,prod_size, user_code, product_tbl.prod_name, product_tbl.prod_category, product_tbl.prod_Decategory, product_tbl.price, product_tbl.prod_score, product_tbl.discount_price, product_tbl.discount_ratio, product_tbl.diliver_price \n" +
            "FROM basket\n" +
            "INNER JOIN product_tbl\n" +
            "ON basket.prod_code = product_tbl.prod_code\n" +
            "WHERE basket.user_code = :userCode" ,nativeQuery = true)
    List<Baskets> findByIDUsercart(int userCode);
}



