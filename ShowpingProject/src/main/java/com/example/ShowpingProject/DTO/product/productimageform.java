package com.example.ShowpingProject.DTO.product;

import com.example.ShowpingProject.entity.product.product;
import com.example.ShowpingProject.entity.product.productimage;
import lombok.Data;

@Data
public class productimageform {
    String image_link;
    String image_link2;
    String image_link3;
    String image_link4;

    public productimageform(String image_link, String image_link2, String image_link3, String image_link4) {
        this.image_link = image_link;
        this.image_link2 = image_link2;
        this.image_link3 = image_link3;
        this.image_link4 = image_link4;
    }

    @Override
    public String toString() {
        return "productimageform{" +
                "image_link='" + image_link + '\'' +
                '}';
    }

    public productimage toEntity(product product){
        return new productimage(product,image_link,image_link2,image_link3,image_link4);
    }
}
