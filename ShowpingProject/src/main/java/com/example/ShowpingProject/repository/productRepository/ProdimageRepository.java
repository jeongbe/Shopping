package com.example.ShowpingProject.repository.productRepository;

import com.example.ShowpingProject.entity.product.productimage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdimageRepository extends CrudRepository<productimage,Long> {
    //상품 코드와 일치하는 이미지를 가져오기 위한 쿼리
    @Query(value = "SELECT image_link, image_link2, image_link3, image_link4" +
            "FROM productimage " +
            "INNER JOIN product" +
            "ON productimage.prod_code = product.prod_code" +
            "WHERE  productimage.prod_code = :prod_code",nativeQuery = true)
     List<productimage> findbyprodcode(long prod_code);

}
