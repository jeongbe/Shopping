package com.example.ShowpingProject.repository.productRepository;

import com.example.ShowpingProject.entity.product.productdeimage;
import com.example.ShowpingProject.entity.product.productimage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProddeimageRepository extends CrudRepository<productdeimage, Long> {
    //상품 코드와 일치하는 이미지를 가져오기 위한 쿼리
    @Query(value = "SELECT deimage_link " +
            "FROM productdeimage " +
            "INNER JOIN product" +
            "ON productdeimage.prod_code = product.prod_code" +
            "WHERE  productdeimage.prod_code = :prod_code",nativeQuery = true)
     List<productdeimage> findbyprodcode(long prod_code);

}
