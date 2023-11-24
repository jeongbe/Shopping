package com.example.ShowpingProject.entity.product;

import jakarta.persistence.*;

@Entity
@Table(name="productdeimage")


public class productdeimage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long deimage_code;

    @OneToOne
    @JoinColumn(name="prod_code", referencedColumnName = "prod_code")
    product product;

    @Column
    String deimage_link;
    @Column
    String deimage_link2;
    @Column
    String deimage_link3;
    @Column
    String deimage_link4;
    @Column
    String deimage_link5;


   public productdeimage(){

   }

    public productdeimage(com.example.ShowpingProject.entity.product.product product, String deimage_link, String deimage_link2, String deimage_link3, String deimage_link4, String deimage_link5) {
        this.deimage_code = deimage_code;
        this.product = product;
        this.deimage_link = deimage_link;
        this.deimage_link2 = deimage_link2;
        this.deimage_link3 = deimage_link3;
        this.deimage_link4 = deimage_link4;
        this.deimage_link5 = deimage_link5;

    }
}
