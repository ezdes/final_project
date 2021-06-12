package com.example.project.Service;

import com.example.project.Entity.Image;
import org.springframework.web.multipart.MultipartFile;


public interface ImageService {
    Image createImage(MultipartFile multipartFile);
    void deleteByName(String name);
}
