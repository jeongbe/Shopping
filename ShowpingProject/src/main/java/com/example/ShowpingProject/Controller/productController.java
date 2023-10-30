package com.example.ShowpingProject.Controller;


import com.example.ShowpingProject.DTO.product.productform;
import com.example.ShowpingProject.entity.product.product;
import com.example.ShowpingProject.repository.productRepository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//상품 등록을 위한 컨트롤러
@Controller
public class productController {
    @Autowired
    ProductRepository productRepository;

    @PostMapping("/shopping/create/product")
    public String createproduct(productform form){
        System.out.println(form.toString());
        product product = form.toEntity();
        System.out.println(product.toString());
        product saved = productRepository.save(product);
        System.out.println(saved.toString());

     return "adminpage/adminmain";

    }



}
