//package com.example.ShowpingProject.DTO;
//
//import com.example.ShowpingProject.entity.Baskets;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@NoArgsConstructor
//
//@Data
//
//public class BasketForm2 {
//
//    Long id;
//
//    Long usercode;   //유저 코드
//
//    Long prodcode;   //상품 코드
//
//    String prodname;   //상품 이름
//    String prodsize;  //상품 사이즈
//
//    String prodcnt; //상품 개수
//
//    int prodprice;  //상품 총 가격
//
//    public BasketForm2(Long id, Long usercode, Long prodcode, String prodname, String prodsize, String prodcnt, int prodprice) {
//        this.id = id;
//        this.usercode = usercode;
//        this.prodcode = prodcode;
//        this.prodname = prodname;
//        this.prodsize = prodsize;
//        this.prodcnt = prodcnt;
//        this.prodprice = prodprice;
//    }
//
//    public Baskets toEntity(){
//        return new Baskets(id,usercode,prodcode,prodname,prodsize,prodcnt,prodprice);
//    }
//}
