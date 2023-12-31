package com.example.ShowpingProject.repository;

import com.example.ShowpingProject.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question, Long> {

    @Query(value = "select  q.qu_code, q.order_id, q.user_code, q.prod_code, q.qu_type, q.qu_title, q.qu_content,q.prod_name, q.qu_answer, q.qu_date_time, q.qu_date, q.qu_image, q.qu_image2, q.qu_image3,q.user_id, q.prod_discount_price,q.an_content\n" +
            "from question q\n" +
            "where q.user_code = :userCode\n" +
            "order by qu_date_time DESC", nativeQuery = true)
    Page<Question> QuestionList(Pageable pageable, String userCode);

    //전체 문의 목록 가져올때
    @Query(value = "select q.qu_code, q.order_id, q.user_code, q.prod_code, q.qu_type, q.qu_title, q.qu_content, q.qu_answer, q.qu_date_time, q.qu_date, q.qu_image, q.qu_image2, q.qu_image3, p.prod_name as prod_name, u.user_id as user_id,p.prod_discount_price \n" +
            "from question q\n" +
            "join users u\n" +
            "on q.user_code = u.user_code\n" +
            "join product p\n" +
            "on q.prod_code = p.prod_code\n" +
            "order by qu_date_time DESC", nativeQuery = true)
    List<Question> AllQuestion();

    //하나의 문의만 가져올때
    @Query(value = "select q.qu_code, q.order_id, q.user_code, q.prod_code, q.qu_type, q.qu_title, q.qu_content, q.qu_answer, q.qu_date_time, q.qu_date, q.qu_image, q.qu_image2, q.qu_image3, p.prod_name as prod_name, u.user_id as user_id , p.prod_discount_price\n" +
            "from question q\n" +
            "join users u\n" +
            "on q.user_code = u.user_code\n" +
            "join product p\n" +
            "on q.prod_code = p.prod_code\n" +
            "where q.qu_code = :QuCode", nativeQuery = true)
    Question oneQustion(String QuCode);
}
