package com.example.project.Service;

import com.example.project.Entity.Product;
import com.example.project.Exception.ResourceNotFoundException;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProduct(Long id) throws ResourceNotFoundException;
    Product createProduct(Product product);
    void deleteProductById(Long id);
    Product updateProductById(Long id, Product product) throws ResourceNotFoundException;
}
