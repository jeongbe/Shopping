package com.example.ShowpingProject.repository.order;

import com.example.ShowpingProject.entity.Baskets;
import com.example.ShowpingProject.entity.OrderDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderDetailRepository extends CrudRepository<OrderDetail, Long> {

    @Query(value = "SELECT id,prod_cnt, basket.prod_code,prod_size, users.user_code, product.prod_name, product.prod_cate, product.prod_decate, product.prod_price, product.prod_discount_ratio, product.prod_discount_price, product.diliver_price, users.user_ID, users.user_name\n" +
            "FROM basket\n" +
            "JOIN product\n" +
            "ON basket.prod_code = product.prod_code\n" +
            "join users\n" +
            "on basket.user_code = users.user_code\n" +
            "WHERE basket.user_code = :userCode", nativeQuery = true)
    List<OrderDetail> orderDetailcartboxList(int userCode);
}
