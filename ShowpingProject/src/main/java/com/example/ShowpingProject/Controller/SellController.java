package com.example.ShowpingProject.Controller;

import com.example.ShowpingProject.DTO.sellerform;
import com.example.ShowpingProject.entity.OrderHeader;
import com.example.ShowpingProject.repository.order.OrderHeaderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class SellController {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @GetMapping("/sellinfo")
    public String sellinfo(sellerform form, Model model){
        System.out.println(form.toString());
        int sellinfo = orderHeaderRepository.sellinfo(form.getDateStart(),form.getDateEnd());
        model.addAttribute("sellinfo", sellinfo);
        System.out.println(sellinfo);


        return "adminpage/adminmain";
    }
}
