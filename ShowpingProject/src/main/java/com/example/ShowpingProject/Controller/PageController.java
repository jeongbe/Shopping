package com.example.ShowpingProject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    //각 페이지 GETMapping 하는 컨트롤러
    @GetMapping("/shopping/main")
    public String shoppingmain() {

        return "main";
    }

    //상품리스트 getmapping
    @GetMapping("/shopping/prodlist")
    public String prodlist() {
        return "Product/productList";
    }

    //상품상세페이지 getmapping
    @GetMapping("/shopping/proddetail")
    public String prodDetail() {
        return "Product/prodDetail";
    }

    //관리자 메인 페이지
    @GetMapping("/shopping/adminmain")
    public String adminmain() {
        return "adminpage/adminmain";
    }


    //상품등록페이지
    @GetMapping("/shopping/adminprod")
    public String adminprod() {
        return "adminpage/adminprod";
    }

    //1:1문의 리스트 페이지
    @GetMapping("/shopping/questionlist")
    public String questionlist(){
        return "adminpage/questionlist";
    }


    //문의 답변하는 페이지
    @GetMapping("/shopping/answer")
    public String answer(){
    return "adminpage/answer";
   }


   //장바구니
    @GetMapping("/shopping/basket")
    public String basket(){
        return "basket/basket";
   }

   //주문 페이지
    @GetMapping("/shopping/order")
    public String order(){return "/order/order";};
}
