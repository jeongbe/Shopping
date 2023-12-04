package com.example.ShowpingProject.Controller;

import com.example.ShowpingProject.DTO.SearchForm;
import com.example.ShowpingProject.entity.SearchData;
import com.example.ShowpingProject.entity.Test2;
import com.example.ShowpingProject.entity.product.product;
import com.example.ShowpingProject.repository.SearchRepository;
import com.example.ShowpingProject.repository.Test2R;
import com.example.ShowpingProject.repository.productRepository.ProdimageRepository;
import com.example.ShowpingProject.repository.productRepository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class SearchController {

    @Autowired
    SearchRepository searchRepository;

//    @ResponseBody
    //상품 검색
    @PostMapping("/shopping/search")
    public String SearchProdName(Model model, SearchForm searchForm){
        
        //검색된 상품 정보들 가져옴
        List<SearchData> prodinfo = searchRepository.ShowSearchProd(searchForm.getSearchText());
//        log.info(prodinfo.toString());
        model.addAttribute("products",prodinfo);

        return "search/search";
    }
}
