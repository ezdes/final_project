package com.example.project.Service;

import com.example.project.Entity.Location;
import com.example.project.Exception.ResourceNotFoundException;
import com.example.project.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public Location getLocation(Long id) throws ResourceNotFoundException {
        return locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find location with id ", id));
    }

    @Override
    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public void deleteLocationById(Long id) {
        locationRepository.deleteById(id);
    }

    @Override
    public Location updateLocationById(Long id, Location location) throws ResourceNotFoundException {
        return locationRepository.findById(id)
                .map(newLocation -> {
                    newLocation.setPostalCode(location.getPostalCode());
                    newLocation.setCity(location.getCity());
                    newLocation.setCountry(location.getCountry());
                    newLocation.setRegion(location.getRegion());
                    return locationRepository.save(newLocation);
                }).orElseThrow(() -> new ResourceNotFoundException("Could not find location with id ", id));
    }
}
