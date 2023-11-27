package com.example.ShowpingProject.Controller;

import com.example.ShowpingProject.DTO.SearchForm;
import com.example.ShowpingProject.entity.product.product;
import com.example.ShowpingProject.repository.productRepository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
public class SearchController {

    @Autowired
    ProductRepository productRepository;

    //    @ResponseBody
    //상품 검색
    @PostMapping("/shopping/search")
    public String SearchProdName(Model model, SearchForm searchForm){

//        log.info(searchForm.toString());

//        log.info(SearchText);

        List<product> products = productRepository.findProduct(searchForm.getSearchText());
//        log.info(products.toString());

        model.addAttribute("products" ,products);

        return "search/search";
    }
}
