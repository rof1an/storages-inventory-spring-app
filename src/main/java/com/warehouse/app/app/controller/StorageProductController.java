package com.warehouse.app.app.controller;

import com.warehouse.app.app.dto.ProductCountRequest;
import com.warehouse.app.app.dto.StorageProductDto;
import com.warehouse.app.app.dto.StorageProductRequest;
import com.warehouse.app.app.mapper.StorageMapper;
import com.warehouse.app.app.mapper.StorageProductMapper;
import com.warehouse.app.app.model.Product;
import com.warehouse.app.app.model.Storage;
import com.warehouse.app.app.model.StorageProduct;
import com.warehouse.app.app.service.ProductService;
import com.warehouse.app.app.service.StorageProductService;
import com.warehouse.app.app.service.StorageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/storages/statement")
public class StorageProductController {
    private final StorageProductService storageProductService;
    private final StorageService storageService;
    private final ProductService productService;
    private final StorageProductMapper storageProductMapper;

    public Product getProduct(Long id) {
        return productService.getProductById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Storage getStorage(Long id) {
        return storageService.getOneStorage(id)
                .orElseThrow(() -> new RuntimeException("Storage not found"));
    }

    @GetMapping
    public List<StorageProductDto> getProductsInStorages() {
        List<StorageProduct> storageProductList = storageProductService.getStorageProductList();

        return storageProductList.stream()
                .map(sp -> {
                    Storage storage = getStorage(sp.getStorage().getStorageId());
                    Product product = getProduct(sp.getProduct().getProductId());

                    return StorageMapper.storageProductModelToDto(
                            storage, product, sp.getProductCount()
                    );

                })
                .collect(Collectors.toList());
    }

    @PostMapping
    public List<StorageProductDto> addStorageProduct(@RequestBody StorageProductRequest request) {
        Storage storage = storageService.getOneStorage(request.getStorageId())
                .orElseThrow(() -> new EntityNotFoundException("storage not found"));
        List<StorageProduct> storageProductList = new ArrayList<>();

        for (ProductCountRequest productCountRequest : request.getProducts()) {
            Product product = productService.getProductById(productCountRequest.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException("product not found"));

            StorageProduct stProduct = new StorageProduct();
            stProduct.setStorage(storage);
            stProduct.setProduct(product);
            stProduct.setProductCount(productCountRequest.getProductCount());
            storageProductList.add(stProduct);
        }

        storageProductList = storageProductService.addStorageProducts(storageProductList);
        return storageProductMapper.mapToDtos(storageProductList);
    }

    @PutMapping("{id}")
    public StorageProductDto setStorageProduct(@RequestBody StorageProductDto storageProductDto, @PathVariable long id) {
        StorageProduct storageProductModel = storageProductMapper.mapToOneModel(storageProductDto);
        StorageProduct existingStorageProduct = storageProductService.getStorageProductById(id);
        System.out.println(storageProductModel.getProduct().getProductId()); //  getProduct() - null

        StorageProduct returnedModel = storageProductService.updateStorageProduct(storageProductModel);
        return storageProductMapper.mapToOneDto(returnedModel);
    }

    @DeleteMapping("{id}")
    public void deleteStorageProduct(@PathVariable("id") StorageProduct storageProduct) {
        storageProductService.deleteStorageProduct(storageProduct);
    }
}
