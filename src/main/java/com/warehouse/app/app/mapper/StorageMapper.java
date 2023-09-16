package com.warehouse.app.app.mapper;

import com.warehouse.app.app.dto.StorageDto;
import com.warehouse.app.app.dto.StorageProductDto;
import com.warehouse.app.app.model.Product;
import com.warehouse.app.app.model.Storage;
import com.warehouse.app.app.model.StorageProduct;


public class StorageMapper {
    public static StorageDto fromStorageModelToDto(Storage storageModel) {
        return new StorageDto(
                storageModel.getStorageId(), storageModel.getStorageName(), storageModel.getStorageLocation()
        );
    }

    public static Storage fromStorageDtoToModel(StorageDto storageDto) {
        return new Storage(storageDto.getId(), storageDto.getName(), storageDto.getAddress());
    }

    public static StorageProductDto storageProductModelToDto(
            Storage storage,
            Product product,
            int productCount
    ) {
        return new StorageProductDto(storage, product, productCount);
    }

//    public static StorageProduct StorageProductDtoToModel(){
//
//    }
}
