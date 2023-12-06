package com.example.ShowpingProject.Controller;

import com.example.ShowpingProject.DTO.ReviewForm;
import com.example.ShowpingProject.entity.OrderDetail;
import com.example.ShowpingProject.entity.OrderHeader;
import com.example.ShowpingProject.entity.Users;
import com.example.ShowpingProject.entity.product.product;
import com.example.ShowpingProject.entity.review.Review;
import com.example.ShowpingProject.repository.ReviewRepository;
import com.example.ShowpingProject.repository.order.OrderDetailRepository;
import com.example.ShowpingProject.repository.order.OrderHeaderRepository;
import com.example.ShowpingProject.repository.productRepository.ProdimageRepository;
import com.example.ShowpingProject.repository.productRepository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

@Controller
@Slf4j
public class ReviewController {

    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    OrderHeaderRepository orderHeaderRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    ProdimageRepository prodimageRepository;


    //리뷰하기 눌렀을때 해당하는 상품에 리뷰를 남기기위한 매핑
    @GetMapping("/mypage/review/write/{order_id}/{prod_code}/{user_code}")
    public String reviewwrite(@PathVariable Long order_id, @PathVariable Long prod_code , Model model, HttpSession session){

        //유저세션 유지
        Users loginUser = (Users) session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);


        //어떤 상품에 대한 리뷰를 남길지 판단하기 위해 해당 상품코드로 가져오기
        OrderDetail review = orderDetailRepository.review(order_id, prod_code);
        log.info(review.toString());

        //모델화 해서 리뷰 남길 상품에 대한 기본적인 정보를 뿌려준다
        model.addAttribute("review",review);

        //날짜 가져 오기
        OrderHeader orderHeader = orderHeaderRepository.findById(order_id).orElse(null);

        //날짜 정보 뿌려주기
        model.addAttribute("orderdate", orderHeader);

        //이미지 가져 오기
        String image = prodimageRepository.reimage(prod_code);

        //이미지 뿌려주기

        model.addAttribute("reimage", image);



        return "mypage/mypageReviewWrite";
    }

    //실제 작성후 리뷰 인설트
    @PostMapping("/review/write/{order_id}/{prod_code}/{user_code}")
    public String reviewinsert(ReviewForm form,@PathVariable Long order_id, @PathVariable Long prod_code, @PathVariable Long user_code ,HttpSession session, Model model,
                               @RequestParam("reviewimage_link") MultipartFile file){

        //서버 이미지링크 변수
        String link =  "\\\\192.168.250.43\\images\\a";
        String image = (link);

       //리뷰 이미지 저장
        try{
        Path filePath = Path.of(image, file.getOriginalFilename());
        Files.copy(file.getInputStream(),filePath,StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e){

            e.printStackTrace();

            return "";
        }

        //유저세션 유지
        Users loginUser = (Users) session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);

        //주문정보와 리뷰를 일치 시키기위해 주문디테일 정보 빼오기
        OrderDetail orderDetail = orderDetailRepository.findById(order_id).orElse(null);

        //해당 주문건에서 주문한 주문번호 상품코드 유저코드,사이즈 값 넣어주며 엔티티로 전환
        Review review = form.toEntity(order_id,prod_code,user_code,orderDetail.getProd_size());

        //리뷰정보 저장하기
        reviewRepository.save(review);

        //리뷰작성후 리뷰리스트페이지로 리다이렉트 할것임
        //우선은 마이페이지 메인으로 반환
        return "redirect:/mypage/reviewlist/" + user_code;
    }


    @GetMapping("/mypage/reviewlist/{user_code}")
    public String  reviewlist(@PathVariable Long user_code, Model model, HttpSession session){

        //유저세션 유지
        Users loginUser = (Users) session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);


       //유저코드에 해당하는 모든 리뷰 가져오기
        ArrayList<Review> reviewlist = reviewRepository.reviewlist(user_code);

        //리뷰리스트에 뿌려주기위한 모델화
        model.addAttribute("reviewlist", reviewlist);



        return "mypage/mypageReviewList";
    }

    //리뷰 삭제 기능
    @GetMapping("/review/delete/{review_num}/{user_code}")
    public String reviewdelete(@PathVariable Long review_num, @PathVariable Long user_code,HttpSession session, Model model){

        //유저세션 유지
        Users loginUser = (Users) session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);

        //삭제할 데이터를 리뷰번호 기준으로 가져오기
       Review reviewdelete = reviewRepository.findById(review_num).orElse(null);


       //삭제할 대상이 존재 할 경우에만 삭제
       if (reviewdelete != null){

           reviewRepository.delete(reviewdelete);

       }

        //삭제후 리다이렉트
        return "redirect:/mypage/reviewlist/" + user_code;
    }

}
