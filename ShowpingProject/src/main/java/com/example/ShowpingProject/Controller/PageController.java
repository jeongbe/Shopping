package com.example.ShowpingProject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    //각 페이지 GETMapping 하는 컨트롤러
    @GetMapping("/showping/main")
    public String main(){

        return "main";
    }
}
