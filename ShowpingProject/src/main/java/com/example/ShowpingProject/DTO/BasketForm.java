package com.example.ShowpingProject.DTO;

import com.example.ShowpingProject.entity.Baskets;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BasketForm {
    Long id;


    int user_code;   //유저 테이블과 연결


    String prod_code;   //상품 테이블과 연결

    String prod_name;
    String prod_size;  //상품 사이즈

    String prod_cnt; //상품 개수

    String prod_price;



//    public Baskets toEntity(){
//        return new Baskets(id,user_code,prod_code,prod_size,prod_cnt);
//    }
}
