package com.example.ShowpingProject.DTO;

import com.example.ShowpingProject.entity.OrderHeader;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Getter
public class OrderHeaderForm {

    Long order_id;

    Long userCode;

    LocalDate orderDate;

    LocalDateTime orderDateTime;

    String totalPrice;

    String userName;

    String userDetailAddr;
    String userDetailAddr2;
    String userDetailAddr3;

    String userAddr;
    String userAddr2;
    String userAddr3;

//    String userId;
//
//    String userEmail;
//
//    String userPasw;

    String userPhone;

    public OrderHeader toEntity(){
        return new OrderHeader(null,userCode,null,null, totalPrice,userName,userDetailAddr,userDetailAddr2,userDetailAddr3,userAddr,userAddr2,userAddr3,userPhone);
    }
}
