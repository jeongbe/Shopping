package com.example.ShowpingProject.Controller;


import com.example.ShowpingProject.DTO.BasketForm;
import com.example.ShowpingProject.DTO.BasketForm2;
import com.example.ShowpingProject.entity.Baskets;
import com.example.ShowpingProject.repository.BasketRepository;
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
import java.util.List;
import java.util.Locale;

@Slf4j
@Controller
public class BasketController {

    @Autowired
//    BasketService basketService;
    BasketRepository basketRepository;

    //장바구니 안에 있는 상품 불러오기
    @GetMapping("/shopping/cartbox/{user_code}")
    public String showcart(@PathVariable("user_code")  int userCode, BasketForm form , Model model) {
//        log.info(form.toString());
        List<Baskets> basketList = basketRepository.findByIDUsercart(userCode);
        if (basketList != null) {
            model.addAttribute("basketList", basketList);
        } else {
        }
        return "basket/basket";
    }

    //장바구니안 상품 전체 삭제
    @GetMapping("/shopping/cartbox/{user_code}/deleteAll")
    public String DeletecartAll(@PathVariable("user_code") int userCode, RedirectAttributes rttr){
        log.info("삭제요청");
        log.info(String.valueOf(userCode));

        List<Baskets> basketList = basketRepository.findByIDUsercart(userCode);
        if (basketList != null) {
            basketRepository.deleteAll(basketList);
            rttr.addFlashAttribute("msg"," 장바구니 상품이 삭제되었습니다");
        } else {
            rttr.addFlashAttribute("Errormsg"," 장바구니 안 상품이 없습니다.");
        }

        return "redirect:/shopping/cartbox/" + userCode;
    }

    //장바구니 낱개 상품 삭제하기
    @GetMapping("/shopping/cartbox/{user_code}/delete/{id}")
    public String deleteProduct(@PathVariable("user_code") int userCode,@PathVariable("id") Long id) {
        log.info("444444444444444444444");
//        log.info(String.valueOf(userCode));
//        log.info(String.valueOf(id));

        Baskets target = basketRepository.findById(id).orElse(null);
        log.info(target.toString());

        if(target != null){
            basketRepository.delete(target);
        }

        // 삭제가 성공했을 때 메시지를 반환합니다.
//        return "redirect:/shopping/cartbox/" + userCode;
        return "redirect:/shopping/cartbox/" + userCode;
    }

    //핫 스벌 수정 넘았다
    //정보 업데이트해서 db에 저장해주기
    @PostMapping("/shopping/cartbox/{id}/test/ajax")
    @ResponseBody
    public String testAjax(BasketForm2 form, @PathVariable("id") Long id){

        log.info(form.toString());

        Baskets basketEntity = form.toEntity();

        log.info(basketEntity.toString());

        int a =  basketRepository.updateprod(basketEntity.getId(),basketEntity.getProd_cnt(),basketEntity.getProd_price());

//        Baskets target = basketRepository.findById(basketEntity.getId()).orElse(null);
//        log.info();
//
//        log.info(String.valueOf(target));
////
//        if(target != null){
//            basketRepository.save(basketEntity);
//        }
        return "redirect:/shopping/cartbox/" + basketEntity.getUser_code();
    }
}
