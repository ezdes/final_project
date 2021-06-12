package com.example.project.Service;

import com.example.project.Entity.Store;
import com.example.project.Exception.ResourceNotFoundException;
import com.example.project.Repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    @Override
    public Store getStore(Long id) throws ResourceNotFoundException {
        return storeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find store with id ", id));
    }

    @Override
    public Store createStore(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public void deleteStoreById(Long id) {
        storeRepository.deleteById(id);
    }

    @Override
    public Store updateStoreById(Long id, Store store) throws ResourceNotFoundException {
        return storeRepository.findById(id)
                .map(newStore -> {
                    newStore.setName(store.getName());
               
                    newStore.setDescription(store.getDescription());
                    newStore.setLocation(store.getLocation());
                    return storeRepository.save(newStore);
                }).orElseThrow(() -> new ResourceNotFoundException("Could not find store with id ", id));
    }
}
