package com.example.ShowpingProject.Controller;

import com.example.ShowpingProject.DTO.MypageSellerform;
import com.example.ShowpingProject.DTO.OrderDetailForm;
import com.example.ShowpingProject.DTO.QuestionForm;
import com.example.ShowpingProject.DTO.sellerform;
import com.example.ShowpingProject.entity.*;
import com.example.ShowpingProject.entity.product.product;
import com.example.ShowpingProject.entity.product.productimage;
import com.example.ShowpingProject.repository.AnswerRepository;
import com.example.ShowpingProject.repository.QuestionRepository;
import com.example.ShowpingProject.repository.Test2R;
import com.example.ShowpingProject.repository.UsersRepository;
import com.example.ShowpingProject.repository.order.OrderDetailRepository;
import com.example.ShowpingProject.repository.order.OrderHeaderRepository;
import com.example.ShowpingProject.repository.productRepository.ProdimageRepository;
import com.example.ShowpingProject.repository.productRepository.ProductRepository;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

    @Autowired
    Test2R test2R;

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
        }


        log.info("pagin"+pagin.toString());
        //최종 저장
        model.addAttribute("pagins",pagin.getContent());
        model.addAttribute("totalElements" ,pagin.getTotalElements());
        model.addAttribute("totalPage" ,pagin.getTotalPages()); //2

        // 이미지 리스트의 리스트
        List<List<productimage>> allImages = new ArrayList<>();

        for (OrderHeader orderHeader : pagin.getContent()) {
            List<productimage> orderImages = prodimageRepository.mypageMainImg(String.valueOf(orderHeader.getOrder_id()));
            allImages.addAll(Collections.singleton(orderImages));
//            log.info(orderImages.toString());
        }

        model.addAttribute("mypageOneImg",allImages);

        // 각 OrderHeader에 대한 디테일 상품 개수 가져오기
        List<String> detailCounts = new ArrayList<>();
        for (OrderHeader orderHeader : pagin.getContent()) {
            String orderId = String.valueOf(orderHeader.getOrder_id());
            String detailCount = orderDetailRepository.OrderDetailCount(orderId);
            detailCounts.add(detailCount);
        }

        log.info(detailCounts.toString());
        // 상품 헤더 안 디테일 개수를 모델에 추가
        model.addAttribute("detailCounts", detailCounts);

        return "mypage/mypageMain";
    }


    //마이페이지 주문헤더 클릭했을때 주문 디테일 보여줌
    @GetMapping("/mypage/orderdetail/{order_id}")
    public String ShowOrderDetail( @PathVariable("order_id")  String orderid,Model model,HttpSession session){
        Users loginUser = (Users) session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);
//        log.info(loginUser.toString());
        //주문 코드 모텔로 만듬
        //        log.info(String.valueOf(orderid));
        model.addAttribute("orderID", orderid);

        //주문번호 기준으로 해당 주문 상세히 보여줌
        List<Test2> showDetail2 = test2R.ShowOrderDetail2(orderid);
        model.addAttribute("showDetailProd",showDetail2);
//        log.info("sdfffffffffffffffffff" + showDetail2);

        return "mypage/mypageProductDetail";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////// --- 문의 ---
    //주문 디테일에서 문의하기 버튼 클릭했을때
    @GetMapping("/mypage/inquiry/write/{order_id}/{prod_code}")
    public String MypageReviewWrite(QuestionForm Qform, @PathVariable("order_id")  String orderId,@PathVariable("prod_code")  String ProdCode,HttpSession session,Model model,OrderHeader OH){

        Users loginUser = (Users) session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);

        //각 해당 상품 문의를 하기위해 상품코드, 주문번호를 사용해서 상품 1개당 1개 문의할 수 있게 일단 상품 정보 가져오는 부분
        OrderDetail OD = orderDetailRepository.InquiryShowProd(orderId,ProdCode);
        String inquiryOneImg = prodimageRepository.oneimg(ProdCode);
//        log.info(inquiryOneImg);
        model.addAttribute("prodImg",inquiryOneImg);
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
    public String writeQ(QuestionForm Qform, @PathVariable("order_id")  String orderId,@PathVariable("prod_code")  String ProdCode,HttpSession session,Model model,
                        @RequestParam(value = "quImage",required = false)MultipartFile file1,
                        @RequestParam(value = "quImage2",required = false)MultipartFile file2,
                        @RequestParam(value = "quImage3",required = false)MultipartFile file3){

        Users loginUser = (Users) session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);

        //서버 이미지링크 변수
        String link =  "\\\\192.168.2.37\\images\\a";


        try {
            if (file1 != null && !file1.isEmpty()) {
                String image1 = link + File.separator + file1.getOriginalFilename();
                Path filePath = Paths.get(image1);
                Files.copy(file1.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            }

            if (file2 != null && !file2.isEmpty()) {
                String image2 = link + File.separator + file2.getOriginalFilename();
                Path filePath2 = Paths.get(image2);
                Files.copy(file2.getInputStream(), filePath2, StandardCopyOption.REPLACE_EXISTING);
            }

            if (file3 != null && !file3.isEmpty()) {
                String image3 = link + File.separator + file3.getOriginalFilename();
                Path filePath3 = Paths.get(image3);
                Files.copy(file3.getInputStream(), filePath3, StandardCopyOption.REPLACE_EXISTING);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

        log.info(Qform.toString());

        //작성한 제목,내용,문의유형
        Question question = Qform.toEntity();
        log.info("엔티티로 바꿀때" + question.toString());


        question.setOrder_id(Long.valueOf(orderId));
        question.setProd_code(Long.valueOf(ProdCode));
        question.setQu_answer("미완료");
        question.setUser_code(loginUser.getUser_code());
        Question question1 = questionRepository.save(question);

        return "redirect:/mypage/inquiry/list/" + loginUser.getUser_code();
    }



    //문의 내역 리스트 가져올때
    @GetMapping("/mypage/inquiry/list/{user_code}")
    public String InquiryList(@PathVariable("user_code")  String UserCode,HttpSession session,Model model,
                                @RequestParam(value = "page",defaultValue = "0")int page){
        Users loginUser = (Users) session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);

        Page<Question> pagin = null;

        pagin = this.pageService.getQuestion(page,UserCode);
        log.info(pagin.toString());
//        log.info(pagin.getContent().toString());

        model.addAttribute("QuestionList",pagin);
        model.addAttribute("TotalPages",pagin.getTotalPages());

//        List<Question> questionList = questionRepository.QuestionList(UserCode);
//        log.info(questionList.toString());

//        model.addAttribute("QuestionList", questionList);


        return "mypage/mypageInquiryList";
    }

    //1:1 마이페이지에서 문의 답변 받은 내용 확인할때
    @GetMapping("/mypage/inquiry/check/{qu_code}")
    public String inquiryCheck(@PathVariable("qu_code")  String QuCode,HttpSession session,Model model,
                                RedirectAttributes redirectAttributes){
        Users loginUser = (Users) session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);

        //1개의 문의 내역 가져옴
        Question getQ = questionRepository.oneQustion(QuCode);
//        log.info(getQ.toString());
        model.addAttribute("getQ",getQ);

        Answer answer = answerRepository.getAnswer(QuCode);

        if (answer == null) {
            // answer가 null인 경우에는 /mypage/inquiry/list/{user_code}로 리다이렉트
//            model.addAttribute("NoAnswer","아직 답변이 미완료된 상태입니다.");
            redirectAttributes.addFlashAttribute("NoAnswer", "아직 답변이 미완료된 상태입니다.");
            return "redirect:/mypage/inquiry/list/" + loginUser.getUser_code();
        }else {
            model.addAttribute("answerText",answer);
            return "mypage/mypageInquiryCheck";

        }

    }


}















