package com.example.ShowpingProject.DTO;

import com.example.ShowpingProject.entity.Baskets;
import com.example.ShowpingProject.entity.product.product;
import com.example.ShowpingProject.entity.product.productimage;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Data
@Getter
public class BasketForm {
    Long id;

    Long usercode;   //유저 코드

    Long prodcode;   //상품 코드

    String prodname;   //상품 이름
    String prodsize;  //상품 사이즈

    String prodcnt; //상품 개수

    String prodprice;  //상품 총 가격

    String totalPrice;






    public Baskets toEntity(){
        return new Baskets(id,usercode,prodcode,prodname,prodsize,prodcnt,prodprice,totalPrice);
    }
}
