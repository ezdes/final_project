package com.example.project.Service;

import com.example.project.Entity.Product;
import com.example.project.Exception.ResourceNotFoundException;
import com.example.project.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) throws ResourceNotFoundException {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find product with id ", id));
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product updateProductById(Long id, Product product) throws ResourceNotFoundException {
        return productRepository.findById(id)
                .map(newProduct -> {
                    newProduct.setName(product.getName());
                    newProduct.setDescription(product.getName());
                    newProduct.setPrice(product.getPrice());
                    newProduct.setWeight(product.getWeight());
                    newProduct.setCategories(product.getCategories());
                    return productRepository.save(newProduct);
                }).orElseThrow(() -> new ResourceNotFoundException("Could not find product with id ", id));
    }
}
