package com.warehouse.app.app.controller;

import com.warehouse.app.app.dto.StorageDto;
import com.warehouse.app.app.dto.StorageProductDto;
import com.warehouse.app.app.mapper.StorageMapper;
import com.warehouse.app.app.model.Product;
import com.warehouse.app.app.model.Storage;
import com.warehouse.app.app.model.StorageProduct;
import com.warehouse.app.app.service.ProductService;
import com.warehouse.app.app.service.StorageProductService;
import com.warehouse.app.app.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/storages")
public class StorageController {
    private final ProductService productService;
    private final StorageService storageService;
    private final StorageProductService storageProductService;

    @GetMapping
    public List<StorageDto> getStorages() {
        List<Storage> storageList = storageService.getAllStorages();

        return storageList.stream()
                .map(StorageMapper::fromStorageModelToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<StorageDto> findStorageById(@PathVariable("id") Long id) {
        Optional<Storage> foundStorage = storageService.getOneStorage(id);

        if (foundStorage.isPresent()) {
            StorageDto storageDto = StorageMapper.fromStorageModelToDto(foundStorage.get());
            return ResponseEntity.ok(storageDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/statement")
    public List<StorageProductDto> getProductsInStorages() {
        List<StorageProduct> storageProductList = storageProductService.getStorageProductList();

        return storageProductList.stream()
                .map(ps -> {
                    Optional<Storage> storage = storageService.getOneStorage(ps.getStorageId());
                    Optional<Product> product = productService.getProductById(ps.getProductId());

                    if (storage.isPresent() && product.isPresent()) {
                        return StorageMapper.storageProductModelToDto(
                                storage.get(), product.get(), ps.getProductCount()
                        );
                    }
                    return null;
                })
                .collect(Collectors.toList());
    }

    public Product getProduct(Long id) {
        return productService.getProductById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Storage getStorage(Long id) {
        return storageService.getOneStorage(id)
                .orElseThrow(() -> new RuntimeException("Storage not found"));
    }

    @PostMapping("/statement")
    public StorageProductDto addStorageProduct(@RequestBody StorageProduct storageProduct) {
        storageProductService.addProductInStorage(storageProduct);

        return StorageMapper.storageProductModelToDto(
                getStorage(storageProduct.getStorageId()),
                getProduct(storageProduct.getProductId()),
                storageProduct.getProductCount()
        );
    }

    @PostMapping
    public StorageDto addStorage(@RequestBody Storage storage) {
        storageService.addStorage(storage);
        return StorageMapper.fromStorageModelToDto(storage);
    }

    @PutMapping("{id}")
    public StorageDto changeStorage(
            @PathVariable("id") Storage storageFromDb,
            @RequestBody Storage storage
    ) {
        BeanUtils.copyProperties(storage, storageFromDb, "id");
        storageService.changeStorage(storageFromDb);
        return StorageMapper.fromStorageModelToDto(storageFromDb);
    }

    @DeleteMapping("{id}")
    public void deleteStorage(@PathVariable("id") Storage storage) {
        storageService.deleteStorage(storage);
    }
}
