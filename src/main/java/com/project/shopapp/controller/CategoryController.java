package com.project.shopapp.controller;

import com.project.shopapp.dto.CategoryDTO;
import com.project.shopapp.model.CategoryModel;
import com.project.shopapp.service.CategoryService;
import com.project.shopapp.service.imp.CategoryServiceImp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/categories")
//@Validated
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<?> addCategory(
            @Valid @RequestBody CategoryDTO categoryDTO,
            BindingResult result
    ) {
        if(result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }
        categoryService.addCategory(categoryDTO);
        return ResponseEntity.ok(categoryDTO);
    }

    @GetMapping("")
    public ResponseEntity<?> getCategories(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        List<CategoryModel> categoryModels = categoryService.getAllCategories();
        return ResponseEntity.ok(categoryModels);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editCategory(
            @PathVariable Long id,
            @RequestBody CategoryDTO categoryDTO
    ) {
        categoryService.updateCategory(id, categoryDTO);
        return ResponseEntity.ok("Edit category successful");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Delete category successful");
    }
}