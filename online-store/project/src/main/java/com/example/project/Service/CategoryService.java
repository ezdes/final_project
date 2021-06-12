package com.example.project.Service;

import com.example.project.Entity.Category;
import com.example.project.Exception.ResourceNotFoundException;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategory(Long id) throws ResourceNotFoundException;
    Category createCategory(Category category);
    void deleteCategoryById(Long id);
    Category updateCategoryById(Long id, Category category) throws ResourceNotFoundException;
}
