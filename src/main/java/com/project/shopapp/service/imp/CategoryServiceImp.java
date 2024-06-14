package com.project.shopapp.service.imp;

import com.project.shopapp.dto.CategoryDTO;
import com.project.shopapp.model.CategoryModel;

import java.util.List;

public interface CategoryServiceImp {
    CategoryModel addCategory(CategoryDTO categoryDTO);
    CategoryModel getCategoryById(Long id);
    List<CategoryModel> getAllCategories();
    CategoryModel updateCategory(Long id, CategoryDTO categoryDTO);
    void deleteCategory(Long id);
}
