package com.example.ShowpingProject.Controller;

import com.example.ShowpingProject.DTO.BasketForm;
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

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class BasketController {

    @Autowired
//    BasketService basketService;
    BasketRepository basketRepository;

    @GetMapping("/shopping/cartbox/{user_code}")
    public String showcart(@PathVariable("user_code")  int userCode, Model model) {


        List<Baskets> basketList = basketRepository.findByIDUsercart(userCode);
        if (basketList != null) {
            model.addAttribute("basketList", basketList);
        } else {
        }
        return "Basket/basket";
    }
}
