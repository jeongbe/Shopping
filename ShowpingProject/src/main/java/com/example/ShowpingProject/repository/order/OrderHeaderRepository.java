package com.example.ShowpingProject.repository.order;

import com.example.ShowpingProject.entity.OrderDetail;
import com.example.ShowpingProject.entity.OrderHeader;
import com.example.ShowpingProject.entity.Users;
import lombok.Data;
import lombok.Value;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Date;
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

    @Query(value = "select a.order_id, a.order_date, a.order_date_time, a.total_price, b.user_code, b.user_name, b.user_addr,b.user_addr2,b.user_addr3,b.user_detail_addr,b.user_detail_addr2,b.user_detail_addr3\n" +
            "from orderheader a\n" +
            "join users b\n" +
            "on a.user_code = b.user_code\n" +
            "where a.order_id = :orderID ", nativeQuery = true)
    OrderHeader OrderHeaer(int orderID);

    //시작 날짜와 종료날짜를 직접선택하였을 경우 매출을 조회하는 쿼리문
    @Query(value = "SELECT Sum(total_price) As total_price \n" +
            "FROM orderheader\n" +
            "WHERE order_date >= :DateStart\n" +
            "AND order_date <= :DateEnd\n", nativeQuery = true)
    String sellinfo(Date DateStart, Date DateEnd);

    //1주일간의 매출을 조회하는 쿼리문
    @Query(value = "SELECT sum(total_price) as total_price\n" +
            "FROM orderheader\n" +
            "WHERE order_date\n" +
            "BETWEEN date_add(now(),INTERVAL -1 WEEK) AND NOW();", nativeQuery = true)
    int sellinfo7();

    //1개월간의 매출을 조회하는 쿼리문
    @Query(value = "SELECT sum(total_price) as total_price\n" +
            "FROM orderheader\n" +
            "WHERE order_date\n" +
            "BETWEEN date_add(now(),INTERVAL -1 Month) AND NOW();\n",nativeQuery = true)
    int sellinfo1();

    //3개월간의 매출을 조회하는 쿼리문
    @Query(value = "SELECT sum(total_price) as total_price\n" +
            "FROM orderheader\n" +
            "WHERE order_date\n" +
            "BETWEEN date_add(now(),INTERVAL -3 Month) AND NOW();" , nativeQuery = true)
    int sellinfo3();

    //1년간의 매출을 조회하는 쿼리문
    @Query(value = "SELECT sum(total_price) as total_price\n" +
            "FROM orderheader\n" +
            "WHERE order_date\n" +
            "BETWEEN date_add(now(),INTERVAL -1 Year) AND NOW();", nativeQuery = true)
    int sellinfo1Y();


    //마이페이지 주문(헤더)내역 전체 조회
    @Query(value = "select a.order_id, a.order_date, a.order_date_time, a.total_price, b.user_code, b.user_name, b.user_addr,b.user_addr2,b.user_addr3,b.user_detail_addr,b.user_detail_addr2,b.user_detail_addr3\n" +
            "from orderheader a\n" +
            "join users b\n" +
            "on a.user_code = b.user_code\n" +
            "where b.user_code = :userCode\n" +
            "AND a.total_price > 0;", nativeQuery = true)
    List<OrderHeader> OrderHeaderCheck(int userCode);

    //마이페이지 주문(헤더) 직접 기간 조회
    @Query(value = "SELECT *\n" +
            "FROM orderheader\n" +
            "where order_date >= :DateStart \n" +
            "AND order_date <= :DateEnd ", nativeQuery = true)
    List<OrderHeader> SellOrderHeader(Date DateStart, Date DateEnd);

    //마이페이지 주문내역 최근 일주일 조회
    @Query(value = "select *\n" +
            "from orderheader\n" +
            "where order_date between date_add(now(),interval -1 week) and now();", nativeQuery = true)
    List<OrderHeader> OneWeek();

    //마이페이지 주문내역 최근 한달 조회
    @Query(value = "select *\n" +
            "from orderheader\n" +
            "where order_date between date_add(now(),interval -1 month) and now();", nativeQuery = true)
    List<OrderHeader> OneMonth();

    //마이페이지 주문내역 최근 3달전 조회
    @Query(value = "select *\n" +
            "from orderheader\n" +
            "where order_date between date_add(now(),interval -3 month) and now();",nativeQuery = true)
    List<OrderHeader> ThreeMonth();


    //마이페이지 주문내역 1년전 조회
    @Query(value = "select *\n" +
            "from orderheader\n" +
            "where order_date between date_add(now(),interval -1 Year) and now();", nativeQuery = true)
    List<OrderHeader> OneYear();
}
