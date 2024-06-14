package com.project.shopapp.service;

import com.project.shopapp.dto.CategoryDTO;
import com.project.shopapp.model.CategoryModel;
import com.project.shopapp.repository.CategoryRepository;
import com.project.shopapp.service.imp.CategoryServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements CategoryServiceImp {
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryModel addCategory(CategoryDTO categoryDTO) {
        CategoryModel categoryModel = CategoryModel
                .builder()
                .name(categoryDTO.getName())
                .build();
        return categoryRepository.save(categoryModel);
    }

    @Override
    public CategoryModel getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public List<CategoryModel> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryModel updateCategory(Long id, CategoryDTO categoryDTO) {
        CategoryModel existingCategory = getCategoryById(id);
        existingCategory.setName(categoryDTO.getName());
        categoryRepository.save(existingCategory);
        return existingCategory;
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
