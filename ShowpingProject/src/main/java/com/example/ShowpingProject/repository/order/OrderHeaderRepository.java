package com.example.ShowpingProject.repository.order;

import com.example.ShowpingProject.entity.OrderHeader;
import com.example.ShowpingProject.entity.Users;
import lombok.Data;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Date;


public interface OrderHeaderRepository extends CrudRepository<OrderHeader, Long> {

//    @Query(value = "select order_id,order_date,order_date_time, total_price, users.user_code, users.user_detail_addr,users.user_detail_addr2, users.user_detail_addr3, users.user_id, users.user_addr, users.user_addr2, users.user_addr3,users.user_email, users.user_name, users.user_password, users.user_phone\n" +
//            "from orderheader\n" +
//            "join users\n" +
//            "on orderheader.user_code = users.user_code\n" +
//            "where orderheader.user_code = :userCode", nativeQuery = true)
//    OrderHeader userAndOrder(int userCode);

//    @Query(value = "", nativeQuery = true)
//    OrderHeader userAndOrder(int userCode);

    @Query(value = "select a.order_id, a.order_date, a.order_date_time, a.total_price, b.user_code, b.user_name, b.user_addr,b.user_addr2,b.user_addr3,b.user_detail_addr,b.user_detail_addr2,b.user_detail_addr3\n" +
            "from orderheader a\n" +
            "join users b\n" +
            "on a.user_code = b.user_code\n" +
            "where a.order_id = :orderID ", nativeQuery = true)
    OrderHeader OrderHeaer(int orderID);


    @Query(value = "SELECT Sum(total_price) As total_price \n" +
            "FROM orderheader\n" +
            "WHERE order_date >= :DateStart\n" +
            "AND order_date <= :DateEnd\n", nativeQuery = true)
    int sellinfo(Date DateStart, Date DateEnd);

}
