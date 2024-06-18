package com.project.shopapp.service;

import com.project.shopapp.dto.ProductDTO;
import com.project.shopapp.dto.ProductImageDTO;
import com.project.shopapp.exception.DataNotFoundException;
import com.project.shopapp.exception.InvalidParamException;
import com.project.shopapp.model.CategoryModel;
import com.project.shopapp.model.ProductImageModel;
import com.project.shopapp.model.ProductModel;
import com.project.shopapp.repository.CategoryRepository;
import com.project.shopapp.repository.ProductImageRepository;
import com.project.shopapp.repository.ProductRepository;
import com.project.shopapp.service.imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements ProductServiceImp {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductImageRepository productImageRepository;

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
    public ProductModel getProductById(Long id) throws Exception{
        return productRepository.findById(id)
                .orElseThrow(()->
                        new DataNotFoundException
                                ("Cannot find product with id " + id));
    }

    @Override
    public Page<ProductModel> getAllProducts(PageRequest pageRequest) {
        // Lấy danh sách sản phẩm theo trang(page) và giới hạn (limit)
        return productRepository.findAll(pageRequest);
    }

    @Override
    public ProductModel updateProduct(
            Long id,
            ProductDTO productDTO) throws Exception{
        ProductModel existingProduct = getProductById(id);
        if(existingProduct != null) {
            //copy các thuộc tính DTO -> Product
            //có thể sử dụng ModelMapper
            CategoryModel existingCategory = categoryRepository
                    .findById(productDTO.getCategoryId())
                            .orElseThrow(() ->
                                    new DataNotFoundException("Category not found id: " + productDTO.getCategoryId()));
            existingProduct.setName(productDTO.getName());
            existingProduct.setCategoryId(existingCategory);
            existingProduct.setPrice(productDTO.getPrice());
            existingProduct.setThumbnail(productDTO.getThumbnail());
            existingProduct.setDescription(productDTO.getDescription());
            return productRepository.save(existingProduct);
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<ProductModel> optionalProductModel = productRepository.findById(id);
        optionalProductModel.ifPresent(productModel -> productRepository.deleteById(productModel.getId()));
    }

    @Override
    public boolean existsProduct(String name) {
        return productRepository.existsByName(name);
    }

    @Override
    public ProductImageModel addProductImage(
            Long productId,
            ProductImageDTO productImageDTO) throws InvalidParamException {
        ProductModel existingProduct = productRepository
                .findById(productImageDTO.getProductId())
                .orElseThrow(()->
                        new RuntimeException(
                                "Category not found id: "
                                        + productImageDTO.getProductId()));
        ProductImageModel newProductImage = new ProductImageModel();
        newProductImage.setProductId(existingProduct);
        newProductImage.setImageUrl(productImageDTO.getImageUrl());
        // Không cho add quá 5 ảnh cho 1 sản phẩm
        int size = productImageRepository.findByProductId(productId).size();
        if(size >= 5) {
            throw new InvalidParamException("Number of images must be <= 5");
        }
        return productImageRepository.save(newProductImage);
    }
}
