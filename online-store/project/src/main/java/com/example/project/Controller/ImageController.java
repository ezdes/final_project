package com.example.project.Controller;

import com.example.project.Entity.Image;
import com.example.project.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageService imageService;


    @PostMapping
    public Image create(@RequestParam(name = "file") MultipartFile multipartFile) {
        return imageService.createImage(multipartFile);
    }
}