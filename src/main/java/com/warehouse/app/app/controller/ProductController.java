package com.warehouse.app.app.controller;

import com.warehouse.app.app.model.Product;
import com.warehouse.app.app.repository.ProductRepository;
import com.warehouse.app.app.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductService productService;

    @GetMapping()
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("{id}")
    public Product getProductById(@PathVariable("id") Product product) {
        return product;
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addOneProduct(product);
    }

    @PutMapping("{id}")
    public Product updateProduct(
            @PathVariable("id") Product productFromDb,
            @RequestBody Product product
    ) {
        Product existingProduct = productService.getProductById(productFromDb.getProductId()).orElseThrow(() ->
                new EntityNotFoundException("Не найден товар с id: " + productFromDb.getProductId()));
        return productService.updateProduct(existingProduct, product);
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable("id") Product product) {
        productRepository.delete(product);
    }
}
