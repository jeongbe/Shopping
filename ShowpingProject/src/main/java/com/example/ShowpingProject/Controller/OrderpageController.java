package com.example.ShowpingProject.Controller;

import com.example.ShowpingProject.DTO.BasketForm;
import com.example.ShowpingProject.DTO.OrderDetailForm;
import com.example.ShowpingProject.DTO.OrderHeaderForm;
import com.example.ShowpingProject.entity.Baskets;
import com.example.ShowpingProject.entity.OrderDetail;
import com.example.ShowpingProject.entity.OrderHeader;
import com.example.ShowpingProject.entity.Users;
import com.example.ShowpingProject.repository.BasketRepository;
import com.example.ShowpingProject.repository.UsersRepository;
import com.example.ShowpingProject.repository.order.OrderDetailRepository;
import com.example.ShowpingProject.repository.order.OrderHeaderRepository;
import com.example.ShowpingProject.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class OrderpageController  {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Autowired
    BasketRepository basketRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    OrderService orderService;

    //이거를 결제하기 눌렀을때 헤어 테이블에 저장해야함
    //장바구니에서 가져온 정보를 주문 헤더 테이블에 저장한다.
    @PostMapping("/order/{user_code}")
    public String order(@PathVariable("user_code")  int userCode,@ModelAttribute OrderHeaderForm HForm, OrderHeader orderHeader,Baskets baskets){


        //주분 헤더 DTO를 엔티티로 바꿈
        OrderHeader HOrder = HForm.toEntity();

        //주문 헤더 엔티티를 DB에 저장
        OrderHeader saved = orderHeaderRepository.save(HOrder);

        //디비에 저장된 주문번호를 가져온다. 주문디테일에 넣어주기 위해서
        Long orderID = saved.getOrder_id();

        // 매개변수로 유저 코드랑 주문 번호를 넘겨줘서 최종 주문디테일 테이블에 저장하게 한다.
        orderService.saveOrderDetail(userCode,orderID);

        return "";
    }


    //    일단 주문 번호 기준으로 가져온다. 주문한 상품 목록과 구매한 유저 정보
    @GetMapping("/order/Payment/{user_code}")
    public String payment(@PathVariable("user_code")  int userCode, Model model){

        log.info("주문페이지에 상품들 넣어줄꺼임");

        //유저 코드 기준으로 장바구니에 담았던 상품을 가져온다.
        List<Baskets> BasketInfo = basketRepository.findByIDUsercart(userCode);
//        log.info(BasketInfo.toString());

        //상품이 있을 경우 model로 저장해서 주문페이지화면에 정보들을 뿌려준다.
        if(BasketInfo != null){
            model.addAttribute("BasketInfo", BasketInfo);
        }

        //주문페이지에 사용자 정보를 뿌려주기 위해 유저코드 기준으로 유저 정보 가져온다.
        Users userInfo = usersRepository.userInfo(userCode);
//        log.info(userInfo.toString());

        //유저 정보가 있을때 model로 저장해서 화면에 정보 뿌려준다.
        if(userInfo != null){
            model.addAttribute("userInfo",userInfo);
        }

        return "order/order";
    }

    @GetMapping("/order/success/{user_code}")
    public String OrderSuccess(@PathVariable("user_code")  int userCode){

        return "order/orderComp";
    }


}
