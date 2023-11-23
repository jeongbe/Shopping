package com.example.ShowpingProject.Controller;

import com.example.ShowpingProject.DTO.BasketForm;
import com.example.ShowpingProject.DTO.OrderDetailForm;
import com.example.ShowpingProject.DTO.OrderHeaderForm;
import com.example.ShowpingProject.DTO.ProdSizeForm;
import com.example.ShowpingProject.DTO.product.productform;
import com.example.ShowpingProject.entity.*;
import com.example.ShowpingProject.entity.product.product;
import com.example.ShowpingProject.repository.BasketRepository;
import com.example.ShowpingProject.repository.PaymentRepository;
import com.example.ShowpingProject.repository.UsersRepository;
import com.example.ShowpingProject.repository.order.OrderDetailRepository;
import com.example.ShowpingProject.repository.order.OrderHeaderRepository;
import com.example.ShowpingProject.repository.productRepository.ProductRepository;
import com.example.ShowpingProject.service.OrderService;
import com.example.ShowpingProject.service.PaymentService;
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
    UsersRepository usersRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;


    //이거를 결제하기 눌렀을때 헤어 테이블에 저장해야함
    //장바구니에서 가져온 정보를 주문 헤더 테이블에 저장한다.
    @PostMapping("/order/{user_code}")
    public String order(@PathVariable("user_code")  int userCode,@ModelAttribute OrderHeaderForm HForm, OrderHeader orderHeader,Baskets baskets){

        //결제하기 눌렀을때 결제페이블에도 값들을 저장해줄꺼임

        //주분 헤더 DTO를 엔티티로 바꿈
        OrderHeader HOrder = HForm.toEntity();

        //주문 헤더 엔티티를 DB에 저장
        OrderHeader saved = orderHeaderRepository.save(HOrder);

        //디비에 저장된 주문번호를 가져온다. 주문디테일에 넣어주기 위해서
        Long orderID = saved.getOrder_id();

        // 매개변수로 유저 코드랑 주문 번호를 넘겨줘서 최종 주문디테일 테이블에 저장하게 한다.
        orderService.saveOrderDetail(userCode,orderID);
        
        //결제테이블에 넣을 총 가격 가져오기
        String totalPrice = saved.getTotal_price();
        
        //결제 테이블에 값넣어주기
        paymentService.insertPayment(userCode,orderID,totalPrice);

        //다 넣고 장바구니 비워줘야함
        basketRepository.DeleteCartbox(userCode);

        return "";
    }

    //바로 상품디테일에서 주문했을때 주문헤더와 디테일에 정보 저장하기
    @PostMapping("/order/auickly/{user_code}/{prod_code}/{prod_size}")
    public String auicklyOrder(@PathVariable("user_code")  int userCode,@PathVariable("prod_size") String prodsize, @PathVariable("prod_code")  String prodCode, @ModelAttribute OrderHeaderForm HForm, ProdSizeForm prsizeF){


        log.info(prsizeF.toString());

        //주분 헤더 DTO를 엔티티로 바꿈
        OrderHeader HOrder = HForm.toEntity();
        log.info(HOrder.toString());

        //주문 헤더에 정보를 저장하기 위해 유저 name이 필요해서  db에서 usercode 기준으로 이름을 가져와서 넣어준다.
        Optional<Users> user = usersRepository.findById(Long.valueOf((userCode)));
        String username = user.get().getUser_name();
        log.info(username);

        HOrder.setUser_name(username);
        //주문 헤더 엔티티를 DB에 저장
        OrderHeader saved = orderHeaderRepository.save(HOrder);
        log.info("주ㅡ묺헤더 ㅇㄴㄴㄴㄴㄴㄴㅌㅊxxxxxxxxx" + saved.toString());


        log.info(prsizeF.toString());

        //상품 디테일에 정보 저장하기
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProd_code(Long.valueOf(prodCode));
        orderDetail.setUser_code(saved.getUserCode());
        orderDetail.setOrder_id(saved.getOrder_id());
        orderDetail.setProd_cnt(String.valueOf(1));
        orderDetail.setProd_name(prsizeF.getPrname());
        orderDetail.setProd_price(prsizeF.getPrprice());
        orderDetail.setProd_size(prsizeF.getPrsize());
        //저장
        orderDetailRepository.save(orderDetail);

        //결제테이블에 저장
        paymentService.insertPayment(userCode,saved.getOrder_id(),prsizeF.getPrprice());

//        //다 넣고 장바구니 비워줘야함
//        basketRepository.deleteById((long) userCode);

        return "";
    }

//    @PostMapping("/order/auickly/{user_code}/{prod_code}/{prod_size}/data")
//    public String data(Model model,@PathVariable("prod_size")  String prodsize,@PathVariable("user_code")  int userCode, @PathVariable("prod_code")  String prodCode, @ModelAttribute OrderHeaderForm HForm, ProdSizeForm prsizeF){
//
//        log.info(prsizeF.toString());
//
//        model.addAttribute("prodINFO", prsizeF);
//        log.info(prsizeF.toString());
//
//        return "";
//    }


    //  장바구니에서 주문페이지로 넘어갈때! 일단 주문 번호 기준으로 가져온다. 주문한 상품 목록과 구매한 유저 정보
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
    
    //아직 사용안함
    @GetMapping("/order/success/{user_code}")
    public String OrderSuccess(@PathVariable("user_code")  int userCode){

        return "order/orderComp";
    }

    //상품 디테일페이지에서 바로 상품 주문하기 클릭했을때 뿌려줄 정보들
    @GetMapping("/order/auickly/payment/{user_code}/{prod_code}/{prod_size}")
    public String AucklyPayment(Model model, @PathVariable("user_code")  int userCode,@PathVariable("prod_size")  String prodsize,@PathVariable("prod_code")  String prodcode,ProdSizeForm form){

        log.info(prodsize);
        
        //유저 정보
        Users oneuserInfo = usersRepository.oneUserInfo(userCode);
//        log.info(oneuserInfo.toString());

        if (oneuserInfo != null){
            model.addAttribute("oneUserInfo",oneuserInfo);
            log.info(String.valueOf(oneuserInfo));
        }
        
        //상품 정보
        product oneProduct = productRepository.oneproduct(prodcode);
//        log.info(oneProduct.toString());

        if(oneProduct != null){
            model.addAttribute("oneProduct", oneProduct);
        }
        
        //선택한 상품 사이즈
        model.addAttribute("prodsize",prodsize);


        return "order/oneorder";
    }
}
