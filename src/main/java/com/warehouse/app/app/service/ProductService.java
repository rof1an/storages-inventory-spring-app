package com.warehouse.app.app.service;

import com.warehouse.app.app.model.Product;
import com.warehouse.app.app.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }
}
