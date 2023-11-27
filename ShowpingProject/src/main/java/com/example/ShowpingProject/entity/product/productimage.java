package com.example.ShowpingProject.entity.product;

import jakarta.persistence.*;
import lombok.ToString;

@Entity
@Table(name = "productimage")
@ToString
public class productimage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long image_code;

    @OneToOne
    @JoinColumn(name="prod_code", referencedColumnName = "prod_code")
    product product;

    @Column
    String image_link;

    @Column
    String image_link2;

    @Column
    String image_link3;

    @Column
    String image_link4;

    public productimage() {
    }

    public productimage(com.example.ShowpingProject.entity.product.product product, String image_link, String image_link2, String image_link3, String image_link4) {
        this.product = product;
        this.image_link = image_link;
        this.image_link2 = image_link2;
        this.image_link3 = image_link3;
        this.image_link4 = image_link4;
    }


}
