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
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    //이거를 결제하기 눌렀을때 헤어 테이블에 저장해야함
    //장바구니에서 가져온 정보를 주문 헤더 테이블에 저장한다.
    @PostMapping("/order/{user_code}")
    public String order(@PathVariable("user_code")  int userCode){

//        log.info(String.valueOf(Oform));
//
//        Baskets baskets = form.toEntity();
//        log.info(baskets.toString());
////
//        Baskets saved = basketRepository.save(baskets);
//        log.info(saved.toString());
        return "redirect:/order/Payment/" + userCode;
    }


    //    일단 주문 번호 기준으로 가져온다. 주문한 상품 목록과 구매한 유저 정보
    @GetMapping("/order/Payment/{user_code}")
    public String payment(@PathVariable("user_code")  int userCode, Model model){

        log.info("주문페이지에 상품들 넣어줄꺼임");

        List<Baskets> BasketInfo = basketRepository.findByIDUsercart(userCode);
        log.info(BasketInfo.toString());

        if(BasketInfo != null){
            model.addAttribute("BasketInfo", BasketInfo);
        }

        Users userInfo = usersRepository.userInfo(userCode);
        log.info(userInfo.toString());

        if(userInfo != null){
            model.addAttribute("userInfo",userInfo);
        }


        return "order/order";
    }


}
