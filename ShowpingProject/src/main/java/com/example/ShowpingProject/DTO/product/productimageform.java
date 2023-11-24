package com.example.ShowpingProject.DTO.product;

import com.example.ShowpingProject.entity.product.product;
import com.example.ShowpingProject.entity.product.productimage;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class productimageform {

    MultipartFile image_link;
    MultipartFile image_link2;
    MultipartFile image_link3;
    MultipartFile image_link4;



    public productimage toEntity(product product){
        return new productimage(product,
                image_link != null ? image_link.getOriginalFilename() : null,
                image_link2 != null ? image_link2.getOriginalFilename() : null,
                image_link3 != null ? image_link3.getOriginalFilename() : null,
                image_link4 != null ? image_link4.getOriginalFilename() : null);
    }
}
