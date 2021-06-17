package com.example.project.Service;

import com.example.project.Entity.Image;
import com.example.project.Exception.ResourceNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ImageService {
    Image createImage(MultipartFile multipartFile);
    void deleteByName(String name);
    List<Image> getAll();
    Image getById(Long id) throws ResourceNotFoundException;
}
