package com.example.ShowpingProject.Controller;


import com.example.ShowpingProject.DTO.Login.Loginform;
import com.example.ShowpingProject.DTO.product.productdeimageform;
import com.example.ShowpingProject.DTO.product.productform;
import com.example.ShowpingProject.DTO.product.productimageform;
import com.example.ShowpingProject.entity.Users;
import com.example.ShowpingProject.entity.product.product;
import com.example.ShowpingProject.entity.product.productdeimage;
import com.example.ShowpingProject.entity.product.productimage;
import com.example.ShowpingProject.entity.review.Review;
import com.example.ShowpingProject.repository.ReviewRepository;
import com.example.ShowpingProject.repository.UsersRepository;
import com.example.ShowpingProject.repository.productRepository.ProddeimageRepository;
import com.example.ShowpingProject.repository.productRepository.ProdimageRepository;
import com.example.ShowpingProject.repository.productRepository.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

//상품 등록을 위한 컨트롤러
@Controller
@Slf4j
public class productController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProdimageRepository prodimageRepository;
    @Autowired
    ProddeimageRepository proddeimageRepository;
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    UsersRepository usersRepository;


    //관리자에서 상품등록하면 db에 상품정보 저장
    //이미지 링크에 관한 정보는 다른 dto,entity를통해 별도의db에 저장됨
    @PostMapping("/shopping/create/product")
    public String createproduct(productform form, productimageform imageform, productdeimageform deimageform){
        //상품 정보에대한 내용을 디비에 저장하는 매서드
        product product = form.toEntity();
        product saved = productRepository.save(product);
        //상품의 이미지를 저장하는 매서드
        productimage productimage = imageform.toEntity(product);
        productimage imagesaved = prodimageRepository.save(productimage);
        //상품의 상세이미지를 저장하는 메서드
        productdeimage productdeimage = deimageform.toEntity(product);
        productdeimage deimagesaved = proddeimageRepository.save(productdeimage);
        return "adminpage/adminmain";
    }

    //등록된 상품을 db에서 가져와 상품상세페이지에 뿌려줌
    @GetMapping("/shopping/read/product/{id}")
    public String readproduct(@PathVariable long id, Model model, Loginform form,HttpSession session){
        //상품 정보를 가져와서 상품코드에 맞는 정보를 뿌려줌
        product productentity = productRepository.findById(id).orElse(null);
        model.addAttribute("product",productentity);
        //해당 상품코드에 해당하는 이미지 정보를 뿌려줌
        productimage productimageentity = prodimageRepository.findById(id).orElse(null);
        model.addAttribute("productimage", productimageentity);
        //해당 상품코드에 해당하는 상세이미지 정보를 뿌려줌
        productdeimage productdeimageentity = proddeimageRepository.findById(id).orElse(null);
        model.addAttribute("productdeimage",productdeimageentity);
        //해당 상품에대한 리뷰정보를 뿌려줌
        ArrayList<Review> review = reviewRepository.review(id);
        model.addAttribute("productreview", review);
        //유저 세션을 유지하기위한 코드
        Users loginUser = (Users) session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);

        return "Product/prodDetail";
    }


    //상품리스트에 리스트로 등록
    @GetMapping("/shopping/productlist")
    public String productlist(Model model){
        List<product> productEntitylist  =  productRepository.findAll();
        model.addAttribute("productlist", productEntitylist);
        return "Product/productList";
    }

    //상품리스트에 리스트로 등록 로그인버전
    @GetMapping("/shopping/productlist/login")
    public String productlistlogin(Model model){
        List<product> productEntitylist  =  productRepository.findAll();
        model.addAttribute("productlist", productEntitylist);
        return "Product/productListlogin";
    }




}
