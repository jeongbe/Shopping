package com.example.ShowpingProject.Controller;

import com.example.ShowpingProject.DTO.MypageSellerform;
import com.example.ShowpingProject.DTO.sellerform;
import com.example.ShowpingProject.entity.OrderDetail;
import com.example.ShowpingProject.entity.OrderHeader;
import com.example.ShowpingProject.entity.Users;
import com.example.ShowpingProject.repository.order.OrderDetailRepository;
import com.example.ShowpingProject.repository.order.OrderHeaderRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
public class MypageController {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    //마이페이지 각 유저 전체 주문 내역(주문헤더)
    @GetMapping("/mypage/main/{user_code}")
    public String MypageController(HttpSession session, Model model, @PathVariable("user_code")  int userCode, MypageSellerform Sform){
        Users loginUser = (Users) session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);

        log.info(Sform.toString());

        List<OrderHeader> orderHeader;

        if (Sform.getDateStart() != null && Sform.getDateEnd() != null) {
            // 사용자가 설정한 기간 조회
            orderHeader = orderHeaderRepository.SellOrderHeader(Sform.getDateStart(), Sform.getDateEnd());
        }else if(Objects.equals(Sform.getDayfilter(), ("1week"))){
            //일주일전
            orderHeader = orderHeaderRepository.OneWeek();
//                log.info(orderHeader.toString());
        }else if(Objects.equals(Sform.getDayfilter(),("1month"))){
            //한달전
            orderHeader = orderHeaderRepository.OneMonth();

        }else if(Objects.equals(Sform.getDayfilter(),("3month"))){
            //3달전
            orderHeader = orderHeaderRepository.ThreeMonth();

        }else if(Objects.equals(Sform.getDayfilter(),("1year"))){
            //1년전
            orderHeader = orderHeaderRepository.OneYear();
        }else {
            // 기본 전체 주문 내역 조회
            orderHeader = orderHeaderRepository.OrderHeaderCheck(userCode);
        }

        //최종 저장
        model.addAttribute("UserOrderChekcHeader", orderHeader);


        return "mypage/mypageMain";
    }

    //마이페이지 주문헤더 클릭했을때 주문 디테일 보여줌
    @GetMapping("/mypage/orderdetail/{order_id}")
    public String ShowOrderDetail( @PathVariable("order_id")  String orderid,Model model){

        log.info(String.valueOf(orderid));
        List<OrderDetail> showDetail = orderDetailRepository.ShowOrderDetail(orderid);
        log.info(showDetail.toString());

        model.addAttribute("showOrderDetail",showDetail);

        return "mypage/mypageOrderdetail";
    }

    @GetMapping("/mypage/review/list/{user_code}")
    public String Mypagereviewlist(HttpSession session, Model model){
        Users loginUser = (Users) session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);

        return "mypage/mypageReviewList";
    }

    @GetMapping("/mypage/like/{user_code}")
    public String Mypagelike(HttpSession session, Model model){
        Users loginUser = (Users) session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);

        return "mypage/mypageLike";
    }

    @GetMapping("/mypage/inquirylist/{user_code}")
    public String Mypageinquitylist(HttpSession session, Model model){
        Users loginUser = (Users) session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);

        return "mypage/mypageInquiryList";
    }

}















