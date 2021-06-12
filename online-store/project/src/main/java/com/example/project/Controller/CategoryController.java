package com.example.project.Controller;

import com.example.project.Entity.Category;
import com.example.project.Exception.ResourceNotFoundException;
import com.example.project.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) throws ResourceNotFoundException {
        return categoryService.getCategory(id);
    }

    @PutMapping("/{id}")
    public Category updateCategoryById(@PathVariable Long id, @RequestBody Category category) throws ResourceNotFoundException {
        return categoryService.updateCategoryById(id, category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }
}
