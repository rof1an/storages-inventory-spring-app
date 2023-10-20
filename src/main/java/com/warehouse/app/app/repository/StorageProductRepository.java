package com.warehouse.app.app.repository;

import com.warehouse.app.app.model.StorageProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageProductRepository extends JpaRepository<StorageProduct, Long> {
}
