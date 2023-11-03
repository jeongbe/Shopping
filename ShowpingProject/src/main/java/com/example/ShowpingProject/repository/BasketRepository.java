package com.example.ShowpingProject.repository;

import com.example.ShowpingProject.entity.Baskets;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


public interface BasketRepository extends CrudRepository<Baskets, Long> {


//    @Query(value = "SELECT id,prod_cnt, basket.prod_code,prod_size, user_tbl.user_code, product_tbl.prod_name, product_tbl.prod_category, product_tbl.prod_Decategory, product_tbl.price, product_tbl.prod_score, product_tbl.discount_price, product_tbl.discount_ratio, product_tbl.diliver_price, user_tbl.user_ID, user_tbl.user_name\n" +
//            "FROM basket\n" +
//            "JOIN product_tbl\n" +
//            "ON basket.prod_code = product_tbl.prod_code\n" +
//            "join user_tbl\n" +
//            "on basket.user_code = user_tbl.user_code\n" +
//            "WHERE basket.user_code = :userCode", nativeQuery = true)
//    List<Baskets> findByIDUsercart(int userCode);

    @Query(value = "SELECT id,prod_cnt, basket.prod_code,prod_size, users.user_code, product.prod_name, product.prod_cate, product.prod_decate, product.prod_price, product.prod_discount_ratio, product.prod_discount_price, product.diliver_price, users.user_ID, users.user_name\n" +
            "FROM basket\n" +
            "JOIN product\n" +
            "ON basket.prod_code = product.prod_code\n" +
            "join users\n" +
            "on basket.user_code = users.user_code\n" +
            "WHERE basket.user_code = :userCode", nativeQuery = true)
    List<Baskets> findByIDUsercart(int userCode);

    //update문 delete문 할때 써줘야함 @Modifying @Transactional
    @Modifying
    @Transactional
    @Query(value = "UPDATE basket \n" +
            "SET prod_cnt = :prodCnt," +
            "prod_discount_price = :price \n" +
            "WHERE id = :id", nativeQuery = true)
    int updateprod(Long id, String prodCnt, int price);
}


