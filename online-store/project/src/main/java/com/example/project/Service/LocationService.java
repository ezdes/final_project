package com.example.project.Service;

import com.example.project.Entity.Location;
import com.example.project.Exception.ResourceNotFoundException;
import java.util.List;

public interface LocationService {
    List<Location> getAllLocations();
    Location getLocation(Long id) throws ResourceNotFoundException;
    Location createLocation(Location location);
    void deleteLocationById(Long id);
    Location updateLocationById(Long id, Location location) throws ResourceNotFoundException;
}
