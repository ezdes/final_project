package com.example.project.Service;

import com.example.project.Entity.Delivery;
import com.example.project.Exception.ResourceNotFoundException;
import com.example.project.Repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }

    @Override
    public Delivery getDelivery(Long id) throws ResourceNotFoundException {
        return deliveryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find delivery with id ", id));
    }

    @Override
    public Delivery createDelivery(Delivery delivery)  {
        return deliveryRepository.save(delivery);
    }

    @Override
    public void deleteDeliveryById(Long id) {
        deliveryRepository.deleteById(id);
    }

    @Override
    public Delivery updateDeliveryById(Long id, Delivery delivery) throws ResourceNotFoundException {
        return deliveryRepository.findById(id)
                .map(newDelivery -> {

                    if (delivery.getUser() != null) {
                        newDelivery.setUser(delivery.getUser());
                    }

                    if (delivery.getCity() != null) {
                        newDelivery.setCity(delivery.getCity());
                    }

                    if (delivery.getCountry() != null) {
                        newDelivery.setCountry(delivery.getCountry());
                    }

                    if (delivery.getRegion() != null) {
                        newDelivery.setRegion(delivery.getCountry());
                    }

                    if (delivery.getStreet() != null) {
                        newDelivery.setStreet(delivery.getStreet());
                    }

                    if (delivery.getHouseNumber() != null) {
                        newDelivery.setHouseNumber(delivery.getHouseNumber());
                    }
                    return deliveryRepository.save(newDelivery);
                }).orElseThrow(() -> new ResourceNotFoundException("Could not find delivery with id ", id));
    }
}
