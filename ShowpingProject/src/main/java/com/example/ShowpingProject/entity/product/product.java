package com.example.ShowpingProject.entity.product;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "product")
@Data
//@SequenceGenerator(
//        name = "prod_code",
//        initialValue = 0
//)

public class product {
    //상품코드 key값 a.i기능
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long prod_code;

    @OneToOne(mappedBy = "product")
    productimage productimage;

    @OneToOne(mappedBy = "product")
    productdeimage productdeimage;

    @Column
    //상품 이름
    String prod_name;
    @Column
    //상품 카테고리
    String prod_cate;
    @Column
    //상품 세부카테고리
    String prod_decate;
    @Column
    //상품 판매가
    String prod_price;
    @Column
    //상품 할인비율
    String prod_discount_ratio;
    @Column
    //상품 할인가격
    String prod_discount_price;
    @Column
    //배달비
    String diliver_price;
    @Column
    //사이즈
     String prod_XS;
    @Column
    //사이즈
    String prod_S;
    @Column
    //사이즈
    String prod_M;
    @Column
    //사이즈
    String prod_L;
    @Column
    //사이즈
    String prod_XL;



    public product() {
    }

    public product(Long prod_code, String prod_name, String prod_cate, String prod_decate, String prod_price, String prod_discount_ratio, String prod_discount_price, String diliver_price, String prod_XS, String prod_S, String prod_M, String prod_L, String prod_XL) {
        this.prod_code = prod_code;
        this.prod_name = prod_name;
        this.prod_cate = prod_cate;
        this.prod_decate = prod_decate;
        this.prod_price = prod_price;
        this.prod_discount_ratio = prod_discount_ratio;
        this.prod_discount_price = prod_discount_price;
        this.diliver_price = diliver_price;
        this.prod_XS = prod_XS;
        this.prod_S = prod_S;
        this.prod_M = prod_M;
        this.prod_L = prod_L;
        this.prod_XL = prod_XL;
    }



    @Override
    public String toString() {
        return "product{" +
                "prod_code=" + prod_code +
                ", prod_name='" + prod_name + '\'' +
                ", prod_cate='" + prod_cate + '\'' +
                ", prod_decate='" + prod_decate + '\'' +
                ", prod_price='" + prod_price + '\'' +
                ", prod_discount_ratio='" + prod_discount_ratio + '\'' +
                ", Prod_discount_price='" + prod_discount_price + '\'' +
                ", diliver_price='" + diliver_price + '\'' +
                ", prod_XS='" + prod_XS + '\'' +
                ", prod_S='" + prod_S + '\'' +
                ", prod_M='" + prod_M + '\'' +
                ", prod_L='" + prod_L + '\'' +
                ", prod_XL='" + prod_XL + '\'' +
                '}';
    }
}
