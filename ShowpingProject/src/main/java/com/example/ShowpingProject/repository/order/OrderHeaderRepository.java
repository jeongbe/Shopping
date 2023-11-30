package com.example.ShowpingProject.repository.order;

import com.example.ShowpingProject.entity.OrderDetail;
import com.example.ShowpingProject.entity.OrderHeader;
import com.example.ShowpingProject.entity.Users;
import lombok.Data;
import lombok.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public interface OrderHeaderRepository extends CrudRepository<OrderHeader, Long> {

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



    //마이페이지 주문(헤더)내역 전체 조회 페이징 처리 완료
    @Query(value = "select a.order_id, a.order_date, a.order_date_time, a.total_price, b.user_code, b.user_name, b.user_addr,b.user_addr2,b.user_addr3,b.user_detail_addr,b.user_detail_addr2,b.user_detail_addr3,a.image_link\n" +
            "from orderheader a\n" +
            "join users b\n" +
            "on a.user_code = b.user_code\n" +
            "where b.user_code = :userCode\n" +
            "AND a.total_price > 0\n", nativeQuery = true)
    Page<OrderHeader> find(Pageable pageable , int userCode);

    //test용
//    @Query(value = "")
//    Page<OrderHeader> find2(Pageable pageable , int userCode);
    //마이페이지 주문(헤더) 직접 기간 조회
    @Query(value = "SELECT *\n" +
            "FROM orderheader\n" +
            "where order_date >= :DateStart \n" +
            "AND order_date <= :DateEnd ", nativeQuery = true)
    Page<OrderHeader> SellOrderHeader(Pageable pageable,Date DateStart, Date DateEnd);

    //마이페이지 주문내역 최근 일주일 조회
    @Query(value = "select *\n" +
            "from orderheader\n" +
            "where order_date between date_add(now(),interval -1 week) and now();", nativeQuery = true)
    Page<OrderHeader> OneWeek(Pageable pageable);

    //마이페이지 주문내역 최근 한달 조회
    @Query(value = "select *\n" +
            "from orderheader\n" +
            "where order_date between date_add(now(),interval -1 month) and now();", nativeQuery = true)
    Page<OrderHeader> OneMonth(Pageable pageable);

    //마이페이지 주문내역 최근 3달전 조회
    @Query(value = "select *\n" +
            "from orderheader\n" +
            "where order_date between date_add(now(),interval -3 month) and now();",nativeQuery = true)
    Page<OrderHeader> ThreeMonth(Pageable pageable);


    //마이페이지 주문내역 1년전 조회
    @Query(value = "select *\n" +
            "from orderheader\n" +
            "where order_date between date_add(now(),interval -1 Year) and now();", nativeQuery = true)
    Page<OrderHeader> OneYear(Pageable pageable);

}
