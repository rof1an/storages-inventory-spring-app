package com.warehouse.app.app.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class StorageProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long storageId;
    private Long productId;
    private int productCount;
}