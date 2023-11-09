package com.example.ShowpingProject.repository.productRepository;

import com.example.ShowpingProject.entity.product.product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<product, Long> {
    @Override
ArrayList<product> findAll();
}
