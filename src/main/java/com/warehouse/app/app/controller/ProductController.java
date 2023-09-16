package com.warehouse.app.app.controller;

import com.warehouse.app.app.model.Product;
import com.warehouse.app.app.repository.ProductRepository;
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

    @GetMapping()
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping("{id}")
    public Product getProductById(@PathVariable("id") Product product) {
        return product;
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        productRepository.save(product);
        return product;
    }

    @PutMapping("{id}")
    public Product changeProduct(
            @PathVariable("id") Product productFromDb,
            @RequestBody Product product
    ) {
        BeanUtils.copyProperties(product, productFromDb, "id");
        return productRepository.save(productFromDb);
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable("id") Product product){
        productRepository.delete(product);
    }

}
