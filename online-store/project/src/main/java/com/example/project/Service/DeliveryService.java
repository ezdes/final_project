package com.example.project.Service;
import com.example.project.Entity.Delivery;
import com.example.project.Exception.ResourceNotFoundException;

import java.util.List;

public interface DeliveryService {
    List<Delivery> getAllDeliveries();
    Delivery getDelivery(Long id) throws ResourceNotFoundException;
    Delivery createDelivery(Delivery delivery) throws ResourceNotFoundException;
    void deleteDeliveryById(Long id);
    Delivery updateDeliveryById(Long id, Delivery delivery) throws ResourceNotFoundException;
}
