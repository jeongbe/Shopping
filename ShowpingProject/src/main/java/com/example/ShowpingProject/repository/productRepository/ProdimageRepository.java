package com.example.ShowpingProject.repository.productRepository;

import com.example.ShowpingProject.entity.product.productimage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProdimageRepository extends CrudRepository<productimage,Long> {

    @Query(value = "SELECT image_link " +
            "FROM productimage " +
            "INNER JOIN product" +
            "ON productimage.prod_code = product.prod_code" +
            "WHERE  productimage.prod_code = :prod_code",nativeQuery = true)
    List<productimage> findbyprodcode(long prod_code);
}
