package com.project.shopapp.service.imp;

import com.project.shopapp.dto.ProductDTO;
import com.project.shopapp.dto.ProductImageDTO;
import com.project.shopapp.exception.DataNotFoundException;
import com.project.shopapp.exception.InvalidParamException;
import com.project.shopapp.model.ProductImageModel;
import com.project.shopapp.model.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ProductServiceImp {
    public ProductModel addProduct(ProductDTO productDTO) throws DataNotFoundException;
    ProductModel getProductById (Long id) throws Exception;
    Page<ProductModel> getAllProducts (PageRequest pageRequest);
    ProductModel updateProduct (Long id, ProductDTO productDTO) throws Exception;
    void deleteProduct (Long id);
    boolean existsProduct (String name);
    public ProductImageModel addProductImage(Long productId, ProductImageDTO productImageDTO) throws InvalidParamException;
}
