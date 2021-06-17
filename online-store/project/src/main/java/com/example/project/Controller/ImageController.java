package com.example.project.Controller;

import com.example.project.Entity.Image;
import com.example.project.Exception.ResourceNotFoundException;
import com.example.project.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping
    public List<Image> getAll() {
        return imageService.getAll();
    }

    @GetMapping("/{id}")
    public Image getById(@PathVariable Long id) throws ResourceNotFoundException {
        return imageService.getById(id);
    }

    @PostMapping
    public Image create(@RequestParam(name = "file") MultipartFile multipartFile) {
        return imageService.createImage(multipartFile);
    }
}