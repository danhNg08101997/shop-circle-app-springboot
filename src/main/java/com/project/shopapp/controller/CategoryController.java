package com.project.shopapp.controller;

import com.project.shopapp.dto.CategoryDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
//@Validated
public class CategoryController {
    @GetMapping("")
    public ResponseEntity<?> getCategories(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        return ResponseEntity.ok(String.format("getCategories page: %d, limit: %d", page, limit));
    }
    @PostMapping("")
    public ResponseEntity<?> addCategory(
            @Valid @RequestBody CategoryDTO category,
            BindingResult result
    ) {
        if(result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }
        return ResponseEntity.ok("Category added " + category.getName());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editCategory(@PathVariable Long id) {
        return ResponseEntity.ok("This is putMapping id " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        return ResponseEntity.ok("This is deleteMapping id " + id);
    }
}