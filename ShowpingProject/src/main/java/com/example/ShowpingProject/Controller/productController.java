package com.example.ShowpingProject.Controller;


import com.example.ShowpingProject.DTO.Login.Loginform;
import com.example.ShowpingProject.DTO.product.productdeimageform;
import com.example.ShowpingProject.DTO.product.productform;
import com.example.ShowpingProject.DTO.product.productimageform;
import com.example.ShowpingProject.entity.OrderDetail;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Array;

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
    //이미지는 서버저장,디비 저장 둘다 처리됨
    //현재 이미지를 다 안넣어주면 예외처리됨(null값처리 아직 안함)
    @PostMapping("/shopping/create/product")
    public String createproduct(productform form, productimageform imageform, productdeimageform deimageform,
                                //대표 이미지
                                @RequestParam("image_link")MultipartFile file,
                                @RequestParam("image_link2")MultipartFile file2,
                                @RequestParam("image_link3")MultipartFile file3,
                                @RequestParam("image_link4")MultipartFile file4,
                                //상세 이미지
                                @RequestParam("deimage_link")MultipartFile defile,
                                @RequestParam("deimage_link2")MultipartFile defile2,
                                @RequestParam("deimage_link3")MultipartFile defile3,
                                @RequestParam("deimage_link4")MultipartFile defile4,
                                @RequestParam("deimage_link5")MultipartFile defile5){



        //서버 이미지링크 변수
        String link =  "\\\\192.168.2.37\\images\\a";


        //대표이미지

        String image =  (link);
        String image2 = (link);
        String image3 = (link);
        String image4 = (link);

        // 상세이미지

        String deimage =  (link);
        String deimage2 = (link);
        String deimage3 = (link);
        String deimage4 = (link);
        String deimage5 = (link);


        try{

            //대표 이미지 저장
            Path filePath = Path.of(image, file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            //대표 이미지 저장 2
            Path filePath2 = Path.of(image2, file2.getOriginalFilename());
            Files.copy(file2.getInputStream(), filePath2, StandardCopyOption.REPLACE_EXISTING);

            //대표 이미지 저장 3
            Path filePath3 = Path.of(image3, file3.getOriginalFilename());
            Files.copy(file3.getInputStream(), filePath3, StandardCopyOption.REPLACE_EXISTING);

            // 대표 이미지 저장 4
            Path filePath4 = Path.of(image4, file4.getOriginalFilename());
            Files.copy(file4.getInputStream(), filePath4, StandardCopyOption.REPLACE_EXISTING);

            //상세 이미지 저장
            Path defilePath = Path.of(deimage, defile.getOriginalFilename());
            Files.copy(defile.getInputStream(), defilePath, StandardCopyOption.REPLACE_EXISTING);

            //상세이미지 저장 2
            Path defilePath2 = Path.of(deimage2, defile3.getOriginalFilename());
            Files.copy(defile2.getInputStream(), defilePath2, StandardCopyOption.REPLACE_EXISTING);

            // 상세 이미지 저장 3
            Path defilePath3 = Path.of(deimage3, defile3.getOriginalFilename());
            Files.copy(defile3.getInputStream(), defilePath3, StandardCopyOption.REPLACE_EXISTING);

            //상세 이미지 저장 4
            Path defilePath4 = Path.of(deimage4, defile4.getOriginalFilename());
            Files.copy(defile4.getInputStream(), defilePath4, StandardCopyOption.REPLACE_EXISTING);

            //상세 이미지 저장 5
            Path defilePath5 = Path.of(deimage5, defile5.getOriginalFilename());
            Files.copy(defile5.getInputStream(), defilePath5, StandardCopyOption.REPLACE_EXISTING);


        }
        catch (IOException e){

            e.printStackTrace();

            return "";

        }

        //상품 정보에대한 내용을 디비에 저장하는 매서드
        product product = form.toEntity();
        product saved = productRepository.save(product);

        //상품의 이미지를 저장하는 매서드
        //product를 매개변수로 데이터를 전달하여 어떤 상품에대한 이미지인지 확인이 가능함
        productimage productimage = imageform.toEntity(product);
        productimage imagesaved = prodimageRepository.save(productimage);

        //상품의 상세이미지를 저장하는 메서드
        //product를 매개변수로 데이터를 전달하여 어떤 상품에대한 이미지인지 확인이 가능함
        productdeimage productdeimage = deimageform.toEntity(product);
        productdeimage deimagesaved = proddeimageRepository.save(productdeimage);
        return "adminpage/adminmain";
    }

    //등록된 상품을 db에서 가져와 상품상세페이지에 뿌려줌
    @GetMapping("/shopping/read/product/{id}")
    public String readproduct(@PathVariable long id, Model model,HttpSession session){

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


        //로그인 세션 유무 판단후 페이지 반환
        //로그인,비로그인 각각 반환페이지가 다름

        if (loginUser != null){
            return "Product/prodDetaillogin";
        }
        else {
            return "Product/prodDetail";
        }


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
    public String productlistlogin(HttpSession session, Model model){
        Users loginUser = (Users) session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);
        List<product> productEntitylist  =  productRepository.findAll();
        model.addAttribute("productlist", productEntitylist);
        return "Product/productListlogin";
    }



}
