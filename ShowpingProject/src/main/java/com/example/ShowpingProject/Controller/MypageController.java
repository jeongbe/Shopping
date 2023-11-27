package com.example.ShowpingProject.Controller;

import com.example.ShowpingProject.DTO.MypageSellerform;
import com.example.ShowpingProject.DTO.QuestionForm;
import com.example.ShowpingProject.DTO.sellerform;
import com.example.ShowpingProject.entity.*;
import com.example.ShowpingProject.entity.product.product;
import com.example.ShowpingProject.entity.product.productimage;
import com.example.ShowpingProject.repository.AnswerRepository;
import com.example.ShowpingProject.repository.QuestionRepository;
import com.example.ShowpingProject.repository.UsersRepository;
import com.example.ShowpingProject.repository.order.OrderDetailRepository;
import com.example.ShowpingProject.repository.order.OrderHeaderRepository;
import com.example.ShowpingProject.repository.productRepository.ProdimageRepository;
import com.example.ShowpingProject.service.PageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @Autowired
    PageService pageService;

    @Autowired
    ProdimageRepository prodimageRepository;

    //마이페이지 각 유저 전체 주문 내역(주문헤더)
    @GetMapping("/mypage/main/{user_code}")
    public String MypageController(HttpSession session, Model model, @PathVariable("user_code")  int userCode, MypageSellerform Sform,
                                        @RequestParam(value="page", defaultValue="0")int page){
        Users loginUser = (Users) session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);

        //유저 정보 가져오기
        Optional<Users> userInfo = usersRepository.findById(Long.valueOf(userCode));
//        log.info(userInfo.toString());

        model.addAttribute("userinfo",userInfo);

        //페이징 처리를 위한 코드
        Page<OrderHeader> pagin = null;

        // 기본 전체 주문 내역 조회
        pagin = this.pageService.getList(page, userCode);

        // 각 OrderHeader에 대한 디테일 상품 개수 가져오기
        List<String> detailCounts = new ArrayList<>();
        for (OrderHeader orderHeader : pagin.getContent()) {
            String orderId = String.valueOf(orderHeader.getOrder_id()); // 이 부분은 실제 getOrderId() 메서드 사용에 맞게 수정
            String detailCount = orderDetailRepository.OrderDetailCount(orderId);
            detailCounts.add(detailCount);
        }

        log.info(detailCounts.toString());
        // 상품 헤더 안 디테일 개수를 모델에 추가
        model.addAttribute("detailCounts", detailCounts);

        //디폴트로 최근 일주일을 고정으로 수정하기
        //기간별로 조회할때 사용함
        if (Sform.getDateStart() != null && Sform.getDateEnd() != null) {
            // 사용자가 설정한 기간 조회
            pagin = this.pageService.getSellList(page,Sform.getDateStart(),Sform.getDateEnd());
        }else if(Objects.equals(Sform.getDayfilter(), ("1week"))){
            //일주일전
            pagin = this.pageService.getOneWeek(page);
        }else if(Objects.equals(Sform.getDayfilter(),("1month"))){
            //한달전
            pagin = this.pageService.getOneMonth(page);
        }else if(Objects.equals(Sform.getDayfilter(),("3month"))){
            //3달전
            pagin = this.pageService.getThreeMonth(page);
        }else if(Objects.equals(Sform.getDayfilter(),("1year"))){
            //1년전
            pagin = this.pageService.getOneYear(page);
        }else if((Objects.equals(Sform.getDayfilter(),("all")))){
            pagin = this.pageService.getList(page,userCode);
        }else {
            // 기본 전체 주문 내역 조회
            pagin = this.pageService.getList(page,userCode);
//            orderHeader = orderHeaderRepository.OrderHeaderCheck(userCode,startPage,endPage);
        }


        log.info(pagin.toString());
        //최종 저장
        model.addAttribute("pagins",pagin.getContent());



        model.addAttribute("totalElements" ,pagin.getTotalElements());
        model.addAttribute("totalPage" ,pagin.getTotalPages()); //2

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

        //디테일 이미지
        List<productimage> Detailimgs = prodimageRepository.DetailImg(orderid);
        log.info(Detailimgs.toString());

        model.addAttribute("detailImg",Detailimgs);

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















