package com.example.ShowpingProject.DTO;

import com.example.ShowpingProject.entity.OrderDetail;

public class OrderDetailForm {

    Long id;

    Long prod_code;


    Long user_code;

    Long order_id;

    String prod_name;

    String prod_size;

    String prod_cnt;

    String prod_price;
    public OrderDetail toEntity(){
        return new OrderDetail(null,prod_code,user_code,order_id,prod_name,prod_size,prod_cnt,prod_price);
    }

}
