package com.example.ShowpingProject.repository.order;

import com.example.ShowpingProject.entity.OrderHeader;
import jakarta.persistence.criteria.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface OrderHeaderRepository extends CrudRepository<OrderHeader, Long> {

//    @Query(value = "select order_id,order_date,order_date_time, total_price, users.user_code, users.user_detail_addr,users.user_detail_addr2, users.user_detail_addr3, users.user_id, users.user_addr, users.user_addr2, users.user_addr3,users.user_email, users.user_name, users.user_password, users.user_phone\n" +
//            "from orderheader\n" +
//            "join users\n" +
//            "on orderheader.user_code = users.user_code\n" +
//            "where orderheader.user_code = :userCode", nativeQuery = true)
//    OrderHeader userAndOrder(int userCode);

//    @Query(value = "", nativeQuery = true)
//    OrderHeader userAndOrder(int userCode);

}
