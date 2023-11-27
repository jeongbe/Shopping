package com.example.ShowpingProject.Controller;


import com.example.ShowpingProject.DTO.BasketForm;
import com.example.ShowpingProject.entity.Baskets;
import com.example.ShowpingProject.entity.Users;
import com.example.ShowpingProject.entity.product.product;
import com.example.ShowpingProject.entity.product.productimage;
import com.example.ShowpingProject.repository.BasketRepository;
import com.example.ShowpingProject.repository.productRepository.ProdimageRepository;
import com.example.ShowpingProject.repository.productRepository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class BasketController {

    @Autowired
//    BasketService basketService;
    BasketRepository basketRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProdimageRepository prodimageRepository;

    // 상품 디테일 페이지에서 장바구니 클릭시 해당상품이 해당 유저 장바구니에 상품이 담김
    @PostMapping("/shopping/insertcartbox/{user_code}/{prod_code}")
    public String insertCartbox(@PathVariable("user_code")  int userCode,@PathVariable("prod_code") String prodCode,BasketForm form){

        log.info(String.valueOf(userCode));
        log.info(prodCode);
        log.info(form.toString());

        Baskets baskets = form.toEntity();
        baskets.setTotal_price(form.getProdprice());
        log.info(baskets.toString());

        Baskets baskets1 = basketRepository.save(baskets);
        log.info(baskets1.toString());

        return "redirect:/shopping/read/product/" + prodCode;
    }

    //장바구니 안에 있는 상품 불러오기 , 유저 코드 기준으로 해당 유저 장바구니 불러옴
    @GetMapping("/shopping/cartbox/{user_code}")
    public String showcart(@PathVariable("user_code")  int userCode, BasketForm form, Model model, HttpSession session) {
        Users loginUser = (Users) session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);
//        log.info(form.toString());
        //null에러 안나게하기위해
        form.setTotalPrice(String.valueOf(0));

        //장바구니 테이블 안에 있는 상품들을 불러온다.
        List<Baskets> basketList = basketRepository.findByIDUsercart(userCode);
        List<productimage> basketImg = prodimageRepository.test(userCode);
//        log.info(basketImg.toString());
        if (basketList != null) {
            //불러온 상품을 장바구니 페이지에서 보여주기 위해서 model basketList에 값들을 넣어준다.
            model.addAttribute("basketList", basketList);
            model.addAttribute("basketIMG",basketImg);

        } else {
            model.addAttribute("basketList", Collections.emptyList());
        }

        return "basket/basket";
    }

    //장바구니안 상품 전체 삭제
    @GetMapping("/shopping/cartbox/{user_code}/deleteAll")
    public String DeletecartAll(@PathVariable("user_code") int userCode, RedirectAttributes rttr){
        log.info("삭제요청");
        log.info(String.valueOf(userCode));

        //장바구니 안에 있는 상품 불러온다.
        List<Baskets> basketList = basketRepository.findByIDUsercart(userCode);
        if (basketList.size() > 0) {
            //해당 유저 장바구니 테이블 안 데이터 전부 삭제한다.
            basketRepository.deleteAll(basketList);
            //삭제 잘 되었을때 해당 메세지를 띄어준다.
            rttr.addFlashAttribute("msg"," 장바구니 상품이 삭제되었습니다");
        } else {
            //상품이 없는데 삭제하려고 할때 메세지를 띄어준다.
            rttr.addFlashAttribute("Errormsg"," 장바구니 안 상품이 없습니다.");
        }


        return "redirect:/shopping/cartbox/" + userCode;
    }
    
    //장바구니 낱개 상품 삭제하기
    @GetMapping("/shopping/cartbox/{user_code}/delete/{id}")
    public String deleteProduct(@PathVariable("user_code") int userCode,@PathVariable("id") Long id) {

        //장바구니 테이블에서 id 기준으로 해당 상품을 target으로 잡는다.
        Baskets target = basketRepository.findById(id).orElse(null);
        log.info(target.toString());

        //타겟이 있을때 해당 상품을 삭제한다.
        if(target != null){
            basketRepository.delete(target);
        }

        return "redirect:/shopping/cartbox/" + userCode;
    }

    //핫 스벌 수정 넘았다
    //정보 업데이트해서 db에 저장해주기
    @PostMapping("/shopping/cartbox/{id}/test/ajax")
    @ResponseBody
    public String testAjax(BasketForm form, @PathVariable("id") Long id, product productE){

        log.info(form.toString());

        //장바구니 DTO를 entity로 바꿔주는 과정
        Baskets basketEntity = form.toEntity();

        log.info(basketEntity.toString());
        
        //장바구니안 상품 수량과 가격 수정해주기
        basketRepository.updateprod(basketEntity.getId(),basketEntity.getProd_cnt(), Integer.parseInt((basketEntity.getTotal_price())));

//        String price = productRepository.fristPrice(prodCode);

        return "redirect:/shopping/cartbox/" + basketEntity.getUser_code();
    }
}
