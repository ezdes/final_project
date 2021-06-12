package com.example.project.Controller;

import com.example.project.Entity.Location;
import com.example.project.Exception.ResourceNotFoundException;
import com.example.project.Service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @GetMapping
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    @GetMapping("/{id}")
    public Location getLocation(@PathVariable Long id) throws ResourceNotFoundException {
        return locationService.getLocation(id);
    }

    @PutMapping("/{id}")
    public Location updateLocationById(@PathVariable Long id, @RequestBody Location location) throws ResourceNotFoundException {
        return locationService.updateLocationById(id, location);
    }

    @DeleteMapping("/{id}")
    public void deleteLocationById(@PathVariable Long id) {
        locationService.deleteLocationById(id);
    }

    @PostMapping
    public Location createLocation(@RequestBody Location location) {
        return locationService.createLocation(location);
    }
}
