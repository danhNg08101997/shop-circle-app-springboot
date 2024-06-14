package com.project.shopapp.repository;

import com.project.shopapp.model.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    // kiểm tra trường name có tồn tại hay không
    boolean existsByName(String name);

    //phân trang
    Page<ProductModel> findAll(Pageable pageable);
}
