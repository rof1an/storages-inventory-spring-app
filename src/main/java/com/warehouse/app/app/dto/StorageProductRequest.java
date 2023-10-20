package com.warehouse.app.app.dto;

import lombok.Data;

import java.util.List;

@Data
public class StorageProductRequest {
    private Long storageId;
    private List<ProductCountRequest> products;
}
