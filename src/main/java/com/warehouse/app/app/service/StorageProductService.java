package com.warehouse.app.app.service;

import com.warehouse.app.app.model.StorageProduct;
import com.warehouse.app.app.repository.StorageProductRepository;
import com.warehouse.app.app.repository.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StorageProductService {
    private final StorageService storageService;
    private final ProductService productService;
    private final StorageProductRepository storageProductRepository;

    public List<StorageProduct> getStorageProductList(){
        return storageProductRepository.findAll();
    }

    public StorageProduct addProductInStorage(StorageProduct storageProduct){
        return storageProductRepository.save(storageProduct);
    }
}
