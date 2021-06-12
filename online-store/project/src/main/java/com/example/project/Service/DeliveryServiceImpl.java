package com.example.project.Service;

import com.example.project.Entity.Delivery;
import com.example.project.Entity.Location;
import com.example.project.Entity.User;
import com.example.project.Enum.RegionCost;
import com.example.project.Exception.ResourceNotFoundException;
import com.example.project.Repository.DeliveryRepository;
import com.example.project.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private LocationService locationService;
    @Autowired
    private UserRepository userRepository;

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
    public Delivery createDelivery(Delivery delivery) throws ResourceNotFoundException {
        Location location = locationService.getLocation(delivery.getLocation().getId());
        delivery.setPrice(location.getRegion().getPrice().doubleValue());
        User user = userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        delivery.setUser(user);
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
                    newDelivery.setUser(delivery.getUser());
                    newDelivery.setLocation(delivery.getLocation());
                    newDelivery.setPrice(delivery.getPrice());
                    return deliveryRepository.save(newDelivery);
                }).orElseThrow(() -> new ResourceNotFoundException("Could not find delivery with id ", id));
    }
}
