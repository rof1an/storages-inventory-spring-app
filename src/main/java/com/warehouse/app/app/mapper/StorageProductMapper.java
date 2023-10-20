package com.warehouse.app.app.mapper;

import com.warehouse.app.app.dto.ProductDto;
import com.warehouse.app.app.dto.StorageDto;
import com.warehouse.app.app.dto.StorageProductDto;
import com.warehouse.app.app.model.Storage;
import com.warehouse.app.app.model.StorageProduct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StorageProductMapper {
    public StorageProductDto mapToDto(StorageProduct sp) {
        return StorageProductDto.builder()
                .product(sp.getProduct())
                .storage(sp.getStorage())
                .productCount(sp.getProductCount())
                .build();
    }

    public List<StorageProductDto> mapToDtos(List<StorageProduct> spList) {
        List<StorageProductDto> storageProductDtos = new ArrayList<>();

        for (StorageProduct sp : spList) {
            storageProductDtos.add(mapToDto(sp));
        }
        return storageProductDtos;
    }

    public StorageProductDto mapToOneDto(StorageProduct storageProduct){
        StorageProductDto dto = new StorageProductDto();

        dto.setProduct(storageProduct.getProduct());
        dto.setStorage(storageProduct.getStorage());
        dto.setProductCount(storageProduct.getProductCount());
        return dto;
    }

    public StorageProduct mapToOneModel(StorageProductDto storageProductDto){
        StorageProduct model = new StorageProduct();

        model.setStorage(storageProductDto.getStorage());
        model.setProduct(storageProductDto.getProduct());
        model.setProductCount(storageProductDto.getProductCount());
        return model;
    }
}

// таблица storageproduct, у склада 2 есть 3 продукта с ид 4
// id, p_id, s_id
// 1,   4,    2
// 2,   4,    2
// 3,   4,    2
// 4,   5,    2