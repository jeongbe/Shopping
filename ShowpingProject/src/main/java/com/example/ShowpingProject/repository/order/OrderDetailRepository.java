package com.example.ShowpingProject.repository.order;

import com.example.ShowpingProject.entity.Baskets;
import com.example.ShowpingProject.entity.OrderDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface OrderDetailRepository extends CrudRepository<OrderDetail, Long> {


    @Query(value = "select a.order_id, a.order_date, a.order_date_time, a.total_price, b.user_code, b.user_name, b.user_addr,b.user_addr2,b.user_addr3,b.user_detail_addr,b.user_detail_addr2,b.user_detail_addr3\n" +
            "from orderheader a\n" +
            "join users b\n" +
            "on a.user_code = b.user_code\n" +
            "where a.order_id = :order_id", nativeQuery = true)
    List<OrderDetail> orderDetailcartboxList(int order_id);


    //각 카테고리별로 최근 1년간의 매출을 조회하는 쿼리문
    @Query(value = "SELECT sum(a.prod_price) as total_price\n" +
            "FROM orderdetail a\n" +
            "INNER JOIN product b\n" +
            "on a.prod_code = b.prod_code\n" +
            "INNER JOIN orderheader c\n" +
            "on a.order_id = c.order_id\n" +
            "WHERE b.prod_decate = \"긴팔\"\n" +
            "AND c.order_date\n" +
            "BETWEEN date_add(now(),INTERVAL -1 Year) AND now();" , nativeQuery = true)
    String longsleeves();

    @Query(value = "SELECT sum(a.prod_price) as total_price\n" +
            "FROM orderdetail a\n" +
            "INNER JOIN product b\n" +
            "on a.prod_code = b.prod_code\n" +
            "INNER JOIN orderheader c\n" +
            "on a.order_id = c.order_id\n" +
            "WHERE b.prod_decate = \"반팔\"\n" +
            "AND c.order_date\n" +
            "BETWEEN date_add(now(),INTERVAL -1 Year) AND now();" , nativeQuery = true)
    String sleeves();

    @Query(value = "SELECT sum(a.prod_price) as total_price\n" +
            "FROM orderdetail a\n" +
            "INNER JOIN product b\n" +
            "on a.prod_code = b.prod_code\n" +
            "INNER JOIN orderheader c\n" +
            "on a.order_id = c.order_id\n" +
            "WHERE b.prod_decate = \"후드\"\n" +
            "AND c.order_date\n" +
            "BETWEEN date_add(now(),INTERVAL -1 Year) AND now();" , nativeQuery = true)
    String hoodie();

    @Query(value = "SELECT sum(a.prod_price) as total_price\n" +
            "FROM orderdetail a\n" +
            "INNER JOIN product b\n" +
            "on a.prod_code = b.prod_code\n" +
            "INNER JOIN orderheader c\n" +
            "on a.order_id = c.order_id\n" +
            "WHERE b.prod_decate = \"맨투맨\"\n" +
            "AND c.order_date\n" +
            "BETWEEN date_add(now(),INTERVAL -1 Year) AND now();" , nativeQuery = true)
    String manto();

    @Query(value = "SELECT sum(a.prod_price) as total_price\n" +
            "FROM orderdetail a\n" +
            "INNER JOIN product b\n" +
            "on a.prod_code = b.prod_code\n" +
            "INNER JOIN orderheader c\n" +
            "on a.order_id = c.order_id\n" +
            "WHERE b.prod_decate = \"니트\"\n" +
            "AND c.order_date\n" +
            "BETWEEN date_add(now(),INTERVAL -1 Year) AND now();" , nativeQuery = true)
    String knit();

    @Query(value = "SELECT sum(a.prod_price) as total_price\n" +
            "FROM orderdetail a\n" +
            "INNER JOIN product b\n" +
            "on a.prod_code = b.prod_code\n" +
            "INNER JOIN orderheader c\n" +
            "on a.order_id = c.order_id\n" +
            "WHERE b.prod_decate = \"데님팬츠\"\n" +
            "AND c.order_date\n" +
            "BETWEEN date_add(now(),INTERVAL -1 Year) AND now();" , nativeQuery = true)
    String denim();

    @Query(value = "SELECT sum(a.prod_price) as total_price\n" +
            "FROM orderdetail a\n" +
            "INNER JOIN product b\n" +
            "on a.prod_code = b.prod_code\n" +
            "INNER JOIN orderheader c\n" +
            "on a.order_id = c.order_id\n" +
            "WHERE b.prod_decate = \"코튼팬츠\"\n" +
            "AND c.order_date\n" +
            "BETWEEN date_add(now(),INTERVAL -1 Year) AND now();" , nativeQuery = true)
    String clothes();

    @Query(value = "SELECT sum(a.prod_price) as total_price\n" +
            "FROM orderdetail a\n" +
            "INNER JOIN product b\n" +
            "on a.prod_code = b.prod_code\n" +
            "INNER JOIN orderheader c\n" +
            "on a.order_id = c.order_id\n" +
            "WHERE b.prod_decate = \"카고팬츠\"\n" +
            "AND c.order_date\n" +
            "BETWEEN date_add(now(),INTERVAL -1 Year) AND now();" , nativeQuery = true)
    String cago();

    @Query(value = "SELECT sum(a.prod_price) as total_price\n" +
            "FROM orderdetail a\n" +
            "INNER JOIN product b\n" +
            "on a.prod_code = b.prod_code\n" +
            "INNER JOIN orderheader c\n" +
            "on a.order_id = c.order_id\n" +
            "WHERE b.prod_decate = \"조거팬츠\"\n" +
            "AND c.order_date\n" +
            "BETWEEN date_add(now(),INTERVAL -1 Year) AND now();" , nativeQuery = true)
    String jogger();

    @Query(value = "SELECT sum(a.prod_price) as total_price\n" +
            "FROM orderdetail a\n" +
            "INNER JOIN product b\n" +
            "on a.prod_code = b.prod_code\n" +
            "INNER JOIN orderheader c\n" +
            "on a.order_id = c.order_id\n" +
            "WHERE b.prod_decate = \"슬렉스\"\n" +
            "AND c.order_date\n" +
            "BETWEEN date_add(now(),INTERVAL -1 Year) AND now();" , nativeQuery = true)
    String slex();

    @Query(value = "SELECT sum(a.prod_price) as total_price\n" +
            "FROM orderdetail a\n" +
            "INNER JOIN product b\n" +
            "on a.prod_code = b.prod_code\n" +
            "INNER JOIN orderheader c\n" +
            "on a.order_id = c.order_id\n" +
            "WHERE b.prod_decate = \"후드집업\"\n" +
            "AND c.order_date\n" +
            "BETWEEN date_add(now(),INTERVAL -1 Year) AND now();" , nativeQuery = true)
    String hoodiejib();

    @Query(value = "SELECT sum(a.prod_price) as total_price\n" +
            "FROM orderdetail a\n" +
            "INNER JOIN product b\n" +
            "on a.prod_code = b.prod_code\n" +
            "INNER JOIN orderheader c\n" +
            "on a.order_id = c.order_id\n" +
            "WHERE b.prod_decate = \"가디건\"\n" +
            "AND c.order_date\n" +
            "BETWEEN date_add(now(),INTERVAL -1 Year) AND now();" , nativeQuery = true)
    String cardigan();

    @Query(value = "SELECT sum(a.prod_price) as total_price\n" +
            "FROM orderdetail a\n" +
            "INNER JOIN product b\n" +
            "on a.prod_code = b.prod_code\n" +
            "INNER JOIN orderheader c\n" +
            "on a.order_id = c.order_id\n" +
            "WHERE b.prod_decate = \"무스탕\"\n" +
            "AND c.order_date\n" +
            "BETWEEN date_add(now(),INTERVAL -1 Year) AND now();" , nativeQuery = true)
    String mustang();

    @Query(value = "SELECT sum(a.prod_price) as total_price\n" +
            "FROM orderdetail a\n" +
            "INNER JOIN product b\n" +
            "on a.prod_code = b.prod_code\n" +
            "INNER JOIN orderheader c\n" +
            "on a.order_id = c.order_id\n" +
            "WHERE b.prod_decate = \"패딩\"\n" +
            "AND c.order_date\n" +
            "BETWEEN date_add(now(),INTERVAL -1 Year) AND now();" , nativeQuery = true)
    String padding();

    @Query(value = "SELECT sum(a.prod_price) as total_price\n" +
            "FROM orderdetail a\n" +
            "INNER JOIN product b\n" +
            "on a.prod_code = b.prod_code\n" +
            "INNER JOIN orderheader c\n" +
            "on a.order_id = c.order_id\n" +
            "WHERE b.prod_decate = \"코트\"\n" +
            "AND c.order_date\n" +
            "BETWEEN date_add(now(),INTERVAL -1 Year) AND now();" , nativeQuery = true)
    String coat();

    //주문 디테일 조회
    @Query(value = "select d.*\n" +
            "from orderdetail d\n" +
            "join orderheader h\n" +
            "on d.order_id = h.order_id\n" +
            "where d.order_id = :orderId",nativeQuery = true)
    List<OrderDetail> ShowOrderDetail(String orderId);

    //1:1 문의 쓰기 할때 해당 상품 정보 가져오는 쿼리
    @Query(value = "select o.* , h.order_date \n" +
            "from orderdetail o\n" +
            "join product p\n" +
            "on o.prod_code = p.prod_code\n" +
            "join orderheader h\n" +
            "on o.order_id = h.order_id\n" +
            "where o.order_id = :orderId \n" +
            "and p.prod_code = :prodCode", nativeQuery = true)
    OrderDetail InquiryShowProd(String orderId, String prodCode);
}
