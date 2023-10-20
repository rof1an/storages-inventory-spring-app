package com.warehouse.app.app.dto;

import lombok.Data;

@Data
public class ProductCountRequest {
    private Long productId;
    private int productCount;
}
