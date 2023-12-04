package com.example.ShowpingProject.repository;

import com.example.ShowpingProject.entity.Test2;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Test2R extends CrudRepository<Test2,Long> {

    @Query(value = "select o.id, o.prod_code, o.user_code, o.order_id, o.prod_name, o.prod_size, o.prod_cnt, o.prod_price, p.image_link as image_link \n" +
            "from orderdetail o\n" +
            "join productimage p\n" +
            "on o.prod_code = p.prod_code\n" +
            "where o.order_id = :orderId", nativeQuery = true)
    List<Test2> ShowOrderDetail2(String orderId);



}
