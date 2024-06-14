package com.project.shopapp.repository;

import com.project.shopapp.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //không cần khai báo cũng được
public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {

}
