package com.example.ShowpingProject.repository;

import com.example.ShowpingProject.DTO.Login.Loginform;
import com.example.ShowpingProject.entity.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users , Long> {

    @Query(value = "SELECT user_id, users.user_code, user_addr, user_addr2, user_addr3, user_detail_addr, user_detail_addr2, user_detail_addr3, user_name, user_phone, user_email, user_password, user_birthday, user_password_check, userbirthday \n" +
            "FROM users\n" +
            "JOIN basket \n" +
            "ON users.user_code = basket.user_code\n" +
            "WHERE users.user_code = :userCode", nativeQuery = true)
    Users userInfo(int userCode);

    //로그인 시도 할때 아이디와 패스워드를 체크하기위한 쿼리문
    @Query(value = "SELECT * " +
            "FROM users " +
            "WHERE user_ID = :login_ID " +
            "And user_password = :login_password ", nativeQuery = true)
    Users login(String login_ID, String login_password);


    @Query(value = "select *\n" +
            "from users u\n" +
            "where u.user_code = :userCode" , nativeQuery = true)
    Users oneUserInfo(int userCode);

}

