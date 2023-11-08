package com.example.ShowpingProject.DTO.product;

import com.example.ShowpingProject.entity.product.product;
import lombok.Data;

@Data
//상품 정보를 가져오는 dto
public class productform {


    //상품 이름
    String prod_name;
    //상품 카테고리
    String prod_cate;
    //상품 세부카테고리
    String prod_decate;
    //상품 판매가
    String prod_price;
    //상품 할인비율
    String prod_discount_ratio;
    //상품 할인가격
    String prod_discount_price;
    //배달비
    String diliver_price;
    //사이즈
    String prod_XS;
    //사이즈
    String prod_S;
    //사이즈
    String prod_M;
    //사이즈
    String prod_L;
    //사이즈
    String prod_XL;

    public productform() {

    }

    public productform(String prod_name, String prod_cate, String prod_decate, String prod_price, String prod_discount_ratio, String prod_discount_price, String diliver_price, String prod_XS, String prod_S, String prod_M, String prod_L, String prod_XL) {
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
        return "productform{" +
                "prod_name='" + prod_name + '\'' +
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

    public product toEntity(){
        if(prod_XS == null){
            prod_XS ="품절";
        }
        if(prod_S == null){
            prod_S = "품절";
        }
        if (prod_M == null){
            prod_M = "품절";
        }
        if (prod_L == null){
            prod_L = "품절";
        }
        if (prod_XL == null){
            prod_XL = "품절";
        }

        switch (prod_cate){
            case "1" : prod_cate="상의"; break;
            case "2" : prod_cate="하의"; break;
            case "3" : prod_cate="아우터";break;
            case "4" : prod_cate="신발"; break;
        }

        return new product(null,prod_name,prod_cate,prod_decate,prod_price,prod_discount_ratio,prod_discount_price, diliver_price,prod_XS,prod_S,prod_M,prod_L,prod_XL);
    }



}

