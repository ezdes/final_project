package com.example.project.Controller;

import com.example.project.Entity.Store;
import com.example.project.Exception.ResourceNotFoundException;
import com.example.project.Service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @GetMapping
    public List<Store> getAllStores() {
        return storeService.getAllStores();
    }

    @GetMapping("/{id}")
    public Store getStoreById(@PathVariable Long id) throws ResourceNotFoundException {
        return storeService.getStore(id);
    }

    @PutMapping("/{id}")
    public Store updateStoreById(@PathVariable Long id, @RequestBody Store store) throws ResourceNotFoundException {
        return storeService.updateStoreById(id, store);
    }

    @DeleteMapping("/{id}")
    public void deleteStoreById(@PathVariable Long id) {
        storeService.deleteStoreById(id);
    }

    @PostMapping
    public Store createStore(@RequestBody Store store) {
        return storeService.createStore(store);
    }
}
