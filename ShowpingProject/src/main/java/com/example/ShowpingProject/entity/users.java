package com.example.ShowpingProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Entity
public class users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long user_code;

    @Column
    String user_ID;

    @Column
    String user_password;

    @Column
    String user_name;

    @Column
    String user_email;

    @Column
    String user_phone;

    @Column
    String user_addr;

    @Column
    String user_detail_addr;

    @Column
    String user_addr2;

    @Column
    String user_detail_addr2;

    @Column
    String user_addr3;

    @Column
    String user_detail_addr3;

}
