package com.example.ShowpingProject.Controller;


import com.example.ShowpingProject.DTO.product.productform;
import com.example.ShowpingProject.entity.product.product;
import com.example.ShowpingProject.repository.productRepository.ProductRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

//상품 등록을 위한 컨트롤러
@Controller
public class productController {
    @Autowired
    ProductRepository productRepository;
    //관리자에서 상품등록하면 db에 상품정보 저장
    @PostMapping("/shopping/create/product")
    public String createproduct(productform form){
        System.out.println(form.toString());
        product product = form.toEntity();
        System.out.println(product.toString());
        product saved = productRepository.save(product);
        System.out.println(saved.toString());
        return "adminpage/adminmain";

    }
    //등록된 상품을 db에서 가져와 상품상세페이지에 뿌려줌
    @GetMapping("/shopping/read/product/{id}")
    public String readproduct(@PathVariable long id,Model model){
        product productentity = productRepository.findById(id).orElse(null);
        model.addAttribute("product",productentity);
        return "Product/prodDetail";
    }

    //상품리스트에 리스트로 등록
    @GetMapping("/shopping/productlist")
    public String productlist(Model model){

        ArrayList<product>productEntitylist  =  productRepository.findAll();
        model.addAttribute("productlist", productEntitylist);
        return "Product/productList";
    }

}
