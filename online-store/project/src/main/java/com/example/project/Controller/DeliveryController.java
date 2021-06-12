package com.example.project.Controller;

import com.example.project.Entity.Delivery;
import com.example.project.Exception.ResourceNotFoundException;
import com.example.project.Service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {
    @Autowired
    private DeliveryService deliveryService;

    @GetMapping
    public List<Delivery> getAllDeliveries() {
        return deliveryService.getAllDeliveries();
    }

    @GetMapping("/{id}")
    public Delivery getDeliveryById(@PathVariable Long id) throws ResourceNotFoundException {
        return deliveryService.getDelivery(id);
    }

    @PutMapping("/{id}")
    public Delivery updateDeliveryById(@PathVariable Long id, @RequestBody Delivery delivery) throws ResourceNotFoundException {
        return deliveryService.updateDeliveryById(id, delivery);
    }

    @DeleteMapping("/{id}")
    public void deleteDeliveryById(@PathVariable Long id) {
        deliveryService.deleteDeliveryById(id);
    }

    @PostMapping
    public Delivery createDelivery(@RequestBody Delivery delivery) throws ResourceNotFoundException {
        return deliveryService.createDelivery(delivery);
    }
}
