package com.example.ShowpingProject.repository.productRepository;

import com.example.ShowpingProject.entity.product.product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<product, Long> {
}
