package com.example.ShowpingProject.DTO.product;


import com.example.ShowpingProject.entity.product.product;
import com.example.ShowpingProject.entity.product.productdeimage;
import com.example.ShowpingProject.entity.product.productimage;
import lombok.Data;

@Data
public class productdeimageform {
    String deimage_link;
    String deimage_link2;
    String deimage_link3;
    String deimage_link4;
    String deimage_link5;
    String deimage_link6;
    String deimage_link7;
    String deimage_link8;


    public productdeimageform(String deimage_link, String deimage_link2, String deimage_link3, String deimage_link4, String deimage_link5, String deimage_link6, String deimage_link7, String deimage_link8) {
        this.deimage_link = deimage_link;
        this.deimage_link2 = deimage_link2;
        this.deimage_link3 = deimage_link3;
        this.deimage_link4 = deimage_link4;
        this.deimage_link5 = deimage_link5;
        this.deimage_link6 = deimage_link6;
        this.deimage_link7 = deimage_link7;
        this.deimage_link8 = deimage_link8;
    }


    public productdeimage toEntity(product product) {
        return new productdeimage(product,deimage_link,deimage_link2,deimage_link3,deimage_link4,deimage_link5,deimage_link6,deimage_link7,deimage_link8);
    }
}

