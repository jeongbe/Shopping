package com.example.ShowpingProject.repository.productRepository;

import com.example.ShowpingProject.entity.product.product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface ProductRepository extends CrudRepository<product, Long> {
    @Override
    ArrayList<product> findAll();

    //상품 코드로 해당 상품 정보 다 가져올때
    @Query(value = "select *\n" +
            "from product\n" +
            "where product.prod_code = :prodCode", nativeQuery = true)
    product oneproduct(String prodCode);

    //상품 이름 겸색할때
    @Query(value = "select *\n" +
            "from product\n" +
            "where prod_name like %:prodname%", nativeQuery = true)
    List<product> findProduct(@Param("prodname") String prodname);
}
