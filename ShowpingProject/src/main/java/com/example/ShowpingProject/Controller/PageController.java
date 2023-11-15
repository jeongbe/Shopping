package com.example.ShowpingProject.Controller;

import com.example.ShowpingProject.entity.Users;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

    //각 페이지 GETMapping 하는 컨트롤러
    @GetMapping("/shopping/main")
    public String shoppingmain() {

        return "main";
    }

    //로그인 완료후 이동하는 페이지
    @GetMapping("/shopping/mainlogin")
    public String shoppingmainlogin(HttpSession session, Model model){
        Users loginUser = (Users) session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);

        return "mainlogin";
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
    public String order(){return "/order/order";}

    //로그인 페이지
    @GetMapping("/shopping/login")
    public String login(){
        return "/login/login";
    }

    //회원가입 동의 페이지
    @GetMapping("/shopping/joinAgree")
    public String joinAgree(){
        return "/login/joinAgree";
    }

    //회원가입 페이지
    @GetMapping("/shopping/joinAgree/join")
    public String join(){
        return "/login/joins";
    }

    //아이디 찾기(결과)
    @GetMapping("/shopping/login/findID")
    public String findID(){
        return "/login/findID";
    }

    //비밀번호 찾기(변경)
    @GetMapping("/shopping/login/findpasw")
    public String findpasw(){
        return "/login/findPasw";
    }

    //아이디 찾기
    @GetMapping("/shopping/login/searchID")
    public String searchID(){
        return "/login/searchID";
    }

    //비밀번호 찾기
    @GetMapping("/shopping/login/searchpasw")
    public String searchpasw(){
        return "/login/searchpasw";
    }

    //주문완료 페이지(결제완료)
    @GetMapping("/shoppng/order/orderComp")
    public String orderComp(){
        return "/order/orderComp";
    }

    //마이페이지 메인 (주문내역조회)
    @GetMapping("/mypage/main")
    public String mypageMain(){
        return "/mypage/mypageMain";
    }

    //마이페이지 내정보수정 비밀번호 체크
    @GetMapping("/mypage/info/check")
    public String mypageInfoCheck() { return "/mypage/mypageInfoCheck";}

    //마이페이지 내정보수정
    @GetMapping("/mypage/info")
    public String mypageInfo() { return "/mypage/mypageInfo";}

    //마이페이지 구매후기 목록
    @GetMapping("/mypage/review/list")
    public String mypageReviewList() { return "/mypage/mypageReviewList";}

    //마이페이지 구매후기 작성
    @GetMapping("/mypage/review/write")
    public String mypageReviewWrite() { return "/mypage/mypageReviewWrite";}

    //마이페이지 구매후기 수정
    @GetMapping("/mypage/review/modify")
    public String mypageReviewModify() { return "/mypage/mypageReviewModify";}

    //마이페이지 구매후기 확인
    @GetMapping("/mypage/review/check")
    public String mypageReviewCheck() { return "mypage/mypageReviewCheck";}

    //마이페이지 좋아요
    @GetMapping("/mypage/like")
    public String mypageLike() { return "mypage/mypageLike";}

    //마이페이지 1:1문의 목록
    @GetMapping("/mypage/inquiry/list")
    public String mypageInquiryList() { return "mypage/mypageInquiryList";}

    //마이페이지 1:1문의 작성
    @GetMapping("/mypage/inquiry/write")
    public String mypageInquiryWrite() { return "mypage/mypageInquiryWrite";}

    //마이페이지 1:1문의 수정
    @GetMapping("/mypage/inquiry/modify")
    public String mypageInquiryModify() { return "mypage/mypageInquiryModify";}

    //마이페이지 1:1문의 확인
    @GetMapping("/mypage/inquiry/check")
    public String mypageInquiryCheck() { return "mypage/mypageInquiryCheck";}

    //마이페이지 1:1문의 답변확인
    @GetMapping("/mypage/inquiry/anser")
    public String mypageInquiryAnser() { return "mypage/mypageInquiryAnswer";}

    //검색 페이지
    @GetMapping("/shopping/searchPage")
    public String serch(){
        return "/search/search";
    }
}
