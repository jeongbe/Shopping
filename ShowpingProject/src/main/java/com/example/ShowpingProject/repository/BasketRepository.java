package com.example.ShowpingProject.repository;

import com.example.ShowpingProject.entity.Baskets;
import com.example.ShowpingProject.entity.product.product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


public interface BasketRepository extends CrudRepository<Baskets, Long> {


    @Query(value = "SELECT b.id, b.prod_cnt, b.prod_code, b.prod_size, u.user_code, p.prod_name, p.prod_cate, p.prod_decate, p.prod_price, p.prod_discount_ratio, b.prod_discount_price, p.diliver_price, u.user_ID, u.user_name, u.user_email, u.user_phone,u.user_addr,u.user_addr2,u.user_addr3,u.user_detail_addr,\n" +
            "u.user_detail_addr2,u.user_detail_addr3, b.total_price, u.userbirthday \n" +
            "FROM basket b\n" +
            "JOIN product p\n" +
            "ON b.prod_code = p.prod_code\n" +
            "join users u\n" +
            "on b.user_code = u.user_code\n" +
            "WHERE b.user_code = :userCode", nativeQuery = true)
    List<Baskets> findByIDUsercart(int userCode);

//


    
    //장바구니 수정 쿼리
    //update문 delete문 할때 써줘야함 @Modifying @Transactional
    @Modifying
    @Transactional
    @Query(value = "UPDATE basket \n" +
            "SET prod_cnt = :prodCnt," +
            "total_price = :price \n" +
            "WHERE id = :id", nativeQuery = true)
    void updateprod(Long id, String prodCnt, int price);

    ArrayList<Baskets> findAllById(int userCode);

    @Modifying
    @Transactional
    @Query(value = "delete from basket where user_code = :userCode", nativeQuery = true)
    void DeleteCartbox(int userCode);

//    Object findAllById(int userCode);
}



