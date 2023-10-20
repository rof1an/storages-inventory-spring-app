package com.warehouse.app.app.service;

import com.warehouse.app.app.dto.StorageProductDto;
import com.warehouse.app.app.mapper.StorageProductMapper;
import com.warehouse.app.app.model.StorageProduct;
import com.warehouse.app.app.repository.StorageProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StorageProductService {
    private final StorageProductRepository storageProductRepository;
    private final StorageProductMapper storageProductMapper;

    public List<StorageProduct> getStorageProductList() {
        return storageProductRepository.findAll();
    }

    public StorageProduct getStorageProductById(long id) {
        return storageProductRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Storage not found"));
    }

    public List<StorageProduct> addStorageProducts(List<StorageProduct> storageProductList) {
        return storageProductRepository.saveAll(storageProductList);
    }

    public StorageProduct updateStorageProduct(StorageProduct storageProduct) {
        StorageProduct builtDto = StorageProduct.builder()
                .product(storageProduct.getProduct())
                .storage(storageProduct.getStorage())
                .productCount(storageProduct.getProductCount())
                .build();

//        StorageProduct builtModel = storageProductMapper.mapToOneModel(builtDto);
        return storageProductRepository.save(builtDto);
    }

    public void deleteStorageProduct(StorageProduct storageProduct) {
        storageProductRepository.delete(storageProduct);
    }
}
