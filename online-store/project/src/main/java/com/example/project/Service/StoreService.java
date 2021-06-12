package com.example.project.Service;
import com.example.project.Entity.Store;
import com.example.project.Exception.ResourceNotFoundException;

import java.util.List;

public interface StoreService {
    List<Store> getAllStores();
    Store getStore(Long id) throws ResourceNotFoundException;
    Store createStore(Store store);
    void deleteStoreById(Long id);
    Store updateStoreById(Long id, Store store) throws ResourceNotFoundException;
}
