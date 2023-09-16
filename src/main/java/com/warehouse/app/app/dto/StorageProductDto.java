package com.warehouse.app.app.dto;

import com.warehouse.app.app.model.Product;
import com.warehouse.app.app.model.Storage;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StorageProductDto {
    private Storage storage;
    private Product product;
    private int productCount;
}
