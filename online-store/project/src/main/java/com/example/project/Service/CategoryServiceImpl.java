package com.example.project.Service;

import com.example.project.Entity.Category;
import com.example.project.Exception.ResourceNotFoundException;
import com.example.project.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(Long id) throws ResourceNotFoundException {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find category with id ", id));
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category updateCategoryById(Long id, Category category) throws ResourceNotFoundException {
        return categoryRepository.findById(id)
                .map(newCategory -> {
                    newCategory.setName(category.getName());
                    newCategory.setDescription(category.getDescription());
                    return categoryRepository.save(newCategory);
                }).orElseThrow(() -> new ResourceNotFoundException("Could not find category with id ", id));
    }
}
