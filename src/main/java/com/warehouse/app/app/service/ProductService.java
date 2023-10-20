package com.warehouse.app.app.service;

import com.warehouse.app.app.model.Product;
import com.warehouse.app.app.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product addOneProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Product existingProduct, Product changedProduct){
        BeanUtils.copyProperties(existingProduct, changedProduct,"id");
        return productRepository.save(existingProduct);
    }
}
