package com.example.ShowpingProject.Controller;

import com.example.ShowpingProject.DTO.MypageSellerform;
import com.example.ShowpingProject.DTO.QuestionForm;
import com.example.ShowpingProject.DTO.sellerform;
import com.example.ShowpingProject.entity.*;
import com.example.ShowpingProject.repository.AnswerRepository;
import com.example.ShowpingProject.repository.QuestionRepository;
import com.example.ShowpingProject.repository.UsersRepository;
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

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Controller
public class MypageController {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    //마이페이지 각 유저 전체 주문 내역(주문헤더)
    @GetMapping("/mypage/main/{user_code}")
    public String MypageController(HttpSession session, Model model, @PathVariable("user_code")  int userCode, MypageSellerform Sform){
        Users loginUser = (Users) session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);

        log.info(Sform.toString());

        //유저 정보 가져오기
        Optional<Users> userInfo = usersRepository.findById(Long.valueOf(userCode));
        log.info(userInfo.toString());

        model.addAttribute("userinfo",userInfo);

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
    public String ShowOrderDetail( @PathVariable("order_id")  String orderid,Model model,HttpSession session){
        Users loginUser = (Users) session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);

        //주문 코드 모텔로 만듬
        log.info(String.valueOf(orderid));
        model.addAttribute("orderID", orderid);

        //주문번호 기준으로 해당 주문 상세히 보여줌
        List<OrderDetail> showDetail = orderDetailRepository.ShowOrderDetail(orderid);
        log.info(showDetail.toString());

        //모델로 만들어서 뷰에 뿌려줌
        model.addAttribute("showOrderDetail",showDetail);

        return "mypage/mypageProductDetail";
    }

    //주문 디테일에서 문의하기 버튼 클릭했을때
    @GetMapping("/mypage/inquiry/write/{order_id}/{prod_code}")
    public String MypageReviewWrite(QuestionForm Qform, @PathVariable("order_id")  String orderId,@PathVariable("prod_code")  String ProdCode,HttpSession session,Model model,OrderHeader OH){

        Users loginUser = (Users) session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);

        //각 해당 상품 문의를 하기위해 상품코드, 주문번호를 사용해서 상품 1개당 1개 문의할 수 있게 일단 상품 정보 가져오는 부분
        OrderDetail OD = orderDetailRepository.InquiryShowProd(orderId,ProdCode);
//        log.info(OD.toString());
        //상품 정보
        model.addAttribute("prodInfo",OD);

        Optional<OrderHeader> OrderH = orderHeaderRepository.findById(Long.valueOf(orderId));
        OrderHeader orderHeader = OrderH.orElse(null);
//        log.info(orderHeader.toString());

        //구매 날짜, 주문 번호
        model.addAttribute("orderInfo",orderHeader);



        return "/mypage/mypageInquiryWrite";
    }

    //문의 제목,내용,문의 타입 보낼때
    @PostMapping("/write/{order_id}/{prod_code}")
    public String writeQ(QuestionForm Qform, @PathVariable("order_id")  String orderId,@PathVariable("prod_code")  String ProdCode,HttpSession session,Model model){

        Users loginUser = (Users) session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);

        //작성한 제목,내용,문의유형
//        log.info(Qform.toString());
        Question question = Qform.toEntity();
        question.setOrder_id(Long.valueOf(orderId));
        question.setProd_code(Long.valueOf(ProdCode));
        question.setQu_answer("미완료");
        question.setUser_code(loginUser.getUser_code());
//        log.info(question.toString());
        Question question1 = questionRepository.save(question);
//        log.info(question1.toString());

        return "redirect:/mypage/inquiry/list/" + loginUser.getUser_code();
    }

    //문의 내역 리스트 가져올때
    @GetMapping("/mypage/inquiry/list/{user_code}")
    public String InquiryList(@PathVariable("user_code")  String UserCode,HttpSession session,Model model){
        Users loginUser = (Users) session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);

        List<Question> questionList = questionRepository.QuestionList(UserCode);
//        log.info(questionList.toString());

        model.addAttribute("QuestionList", questionList);


        return "mypage/mypageInquiryList";
    }

    //1:1 마이페이지에서 문의 답변 받은 내용 확인할때
    @GetMapping("/mypage/inquiry/check/{qu_code}")
    public String inquiryCheck(@PathVariable("qu_code")  String QuCode,HttpSession session,Model model){
        Users loginUser = (Users) session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);



        //1개의 문의 내역 가져옴
        Question getQ = questionRepository.oneQustion(QuCode);
        log.info(getQ.toString());
        model.addAttribute("getQ",getQ);

        Answer answer = answerRepository.getAnswer(QuCode);
//        log.info(answer.toString());
        if (answer == null) {
            // answer가 null인 경우에는 /mypage/inquiry/list/{user_code}로 리다이렉트
            return "redirect:/mypage/inquiry/list/" + loginUser.getUser_code();
        }

        model.addAttribute("answerText",answer);


        return "mypage/mypageInquiryCheck";
    }



//    @GetMapping("/mypage/review/list/{user_code}")
//    public String Mypagereviewlist(HttpSession session, Model model){
//        Users loginUser = (Users) session.getAttribute("loginUser");
//        model.addAttribute("loginUser", loginUser);
//
//
//
//
//        return "mypage/mypageReviewList";
//    }

//    @GetMapping("/mypage/like/{user_code}")
//    public String Mypagelike(HttpSession session, Model model){
//        Users loginUser = (Users) session.getAttribute("loginUser");
//        model.addAttribute("loginUser", loginUser);
//
//        return "mypage/mypageLike";
//    }


}















