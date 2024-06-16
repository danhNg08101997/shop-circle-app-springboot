package com.project.shopapp.service;

import com.project.shopapp.dto.ProductDTO;
import com.project.shopapp.exception.DataNotFoundException;
import com.project.shopapp.model.CategoryModel;
import com.project.shopapp.model.ProductModel;
import com.project.shopapp.repository.CategoryRepository;
import com.project.shopapp.repository.ProductRepository;
import com.project.shopapp.service.imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements ProductServiceImp {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ProductModel addProduct(ProductDTO productDTO) throws DataNotFoundException{
        CategoryModel existingCategory = categoryRepository
                .findById(productDTO.getCategoryId())
                .orElseThrow(()->
                        new RuntimeException(
                                "Category not found id: "
                                        + productDTO.getCategoryId()));
        ProductModel newProduct = ProductModel.builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .thumbnail(productDTO.getThumbnail())
                .categoryId(existingCategory)
                .build();
        return productRepository.save(newProduct);
    }

    @Override
    public ProductModel getProductById(Long id) {
        return null;
    }

    @Override
    public Page<ProductModel> getAllProducts(PageRequest pageRequest) {
        return null;
    }

    @Override
    public ProductModel updateProduct(Long id, ProductDTO productDTO) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public boolean existsProduct(String name) {
        return false;
    }
}
