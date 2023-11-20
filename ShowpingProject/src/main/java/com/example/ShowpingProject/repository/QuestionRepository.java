package com.example.ShowpingProject.repository;

import com.example.ShowpingProject.entity.Baskets;
import com.example.ShowpingProject.entity.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface QuestionRepository extends CrudRepository<Question, Long> {

    @Query(value = "select q.*\n" +
            "from question q\n" +
            "where q.user_code = :userCode", nativeQuery = true)
    List<Question> QuestionList(String userCode);

    @Query(value = "select q.qu_code, q.order_id, q.user_code, q.prod_code, q.qu_type, q.qu_title, q.qu_content, q.qu_answer, q.qu_date_time, q.qu_date, q.qu_image, q.qu_image2, q.qu_image3, p.prod_name as prod_name, u.user_id as user_id \n" +
            "from question q\n" +
            "join users u\n" +
            "on q.user_code = u.user_code\n" +
            "join product p\n" +
            "on q.prod_code = p.prod_code", nativeQuery = true)
    List<Question> AllQuestion();
}
