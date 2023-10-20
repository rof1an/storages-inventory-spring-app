package com.warehouse.app.app.service;

import com.warehouse.app.app.model.Storage;
import com.warehouse.app.app.model.StorageProduct;
import com.warehouse.app.app.repository.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StorageService {
    private final StorageRepository storageRepository;

    public List<Storage> getAllStorages() {
        return storageRepository.findAll();
    }

    public Optional<Storage> getOneStorage(Long id) {
        return storageRepository.findById(id);
    }

    public Storage addStorage(Storage newStorage) {
        storageRepository.save(newStorage);
        return newStorage;
    }

    public Storage changeStorage(Storage changedStorage){
        return storageRepository.save(changedStorage);
    }

    public void deleteStorage(Storage storage){
        storageRepository.delete(storage);
    }
}
