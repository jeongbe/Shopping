package com.example.ShowpingProject.repository;

import com.example.ShowpingProject.entity.SearchData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SearchRepository extends CrudRepository<SearchData,Long> {

    //    //검색 쿼리
    @Query(value = "select p.prod_name,p.prod_discount_price, i.image_link,p.prod_code\n" +
            "from product p\n" +
            "join productimage i\n" +
            "on p.prod_code = i.prod_code\n" +
            "where prod_name like %:prodname%", nativeQuery = true)
    List<SearchData> ShowSearchProd(@Param("prodname") String prodname);
}
