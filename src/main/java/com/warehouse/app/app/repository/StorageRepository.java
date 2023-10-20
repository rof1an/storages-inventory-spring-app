package com.warehouse.app.app.repository;

import com.warehouse.app.app.model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {
//    @Query("SELECT sp FROM StorageProduct sp")
//    List<StorageProduct> getProductsInStorage();
}
