package com.example.ShowpingProject.repository.joins;

import com.example.ShowpingProject.entity.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public  interface JoinsRepository extends CrudRepository<Users, Long> {
    @Query(value = "SELECT * FROM users WHERE user_id = ?1", nativeQuery = true)
    Users findByUserId(String userid);
}