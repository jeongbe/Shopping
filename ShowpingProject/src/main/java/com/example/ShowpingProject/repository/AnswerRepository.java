package com.example.ShowpingProject.repository;

import com.example.ShowpingProject.entity.Answer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends CrudRepository<Answer,Long> {

    @Query(value = "select *\n" +
            "from answer\n" +
            "where answer.qu_code = :QuCode", nativeQuery = true)
    Answer getAnswer(String QuCode);
}
