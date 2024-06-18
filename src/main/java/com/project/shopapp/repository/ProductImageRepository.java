package com.project.shopapp.repository;

import com.project.shopapp.model.ProductImageModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImageModel, Long> {
    List<ProductImageModel> findByProductId(Long productId);
}
