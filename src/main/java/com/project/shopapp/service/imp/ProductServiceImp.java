package com.project.shopapp.service.imp;

import com.project.shopapp.dto.ProductDTO;
import com.project.shopapp.exception.DataNotFoundException;
import com.project.shopapp.model.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ProductServiceImp {
    public ProductModel addProduct(ProductDTO productDTO) throws DataNotFoundException;
    ProductModel getProductById (Long id);
    Page<ProductModel> getAllProducts (PageRequest pageRequest);
    ProductModel updateProduct (Long id, ProductDTO productDTO);
    void deleteProduct (Long id);
    boolean existsProduct (String name);
}
