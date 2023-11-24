package com.example.ShowpingProject.DTO.product;


import com.example.ShowpingProject.entity.product.product;
import com.example.ShowpingProject.entity.product.productdeimage;
import com.example.ShowpingProject.entity.product.productimage;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class productdeimageform {
    MultipartFile deimage_link;
    MultipartFile deimage_link2;
    MultipartFile deimage_link3;
    MultipartFile deimage_link4;
    MultipartFile deimage_link5;





    public productdeimage toEntity(product product) {
        return new productdeimage(product,
                deimage_link != null ? deimage_link.getOriginalFilename() : null,
                deimage_link2 != null ? deimage_link2.getOriginalFilename() : null,
                deimage_link3 != null ? deimage_link3.getOriginalFilename() : null,
                deimage_link4 != null ? deimage_link4.getOriginalFilename() : null,
                deimage_link5 != null ? deimage_link5.getOriginalFilename() : null);
    }
}

