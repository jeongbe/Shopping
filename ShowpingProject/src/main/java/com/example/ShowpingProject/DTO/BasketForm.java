package com.example.ShowpingProject.DTO;

import com.example.ShowpingProject.entity.Baskets;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Getter
public class BasketForm {
    Long id;

    Long usercode;   //유저 코드

    Long prodcode;   //상품 코드

    String prodname;   //상품 이름
    String prodsize;  //상품 사이즈

    String prodcnt; //상품 개수

    int prodprice;  //상품 총 가격

    String totalPrice;

//    public BasketForm(Long id, Long usercode, Long prodcode, String prodname, String prodsize, String prodcnt, int prodprice) {
//
//        this.id = id;
//        this.usercode = usercode;
//        this.prodcode = prodcode;
//        this.prodname = prodname;
//        this.prodsize = prodsize;
//        this.prodcnt = prodcnt;
//        this.prodprice = prodprice;
//    }

    public Baskets toEntity(){
        return new Baskets(id,usercode,prodcode,prodname,prodsize,prodcnt,prodprice,totalPrice);
    }
}
