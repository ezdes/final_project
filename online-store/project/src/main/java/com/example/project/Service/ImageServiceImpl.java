package com.example.project.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.project.Entity.Image;
import com.example.project.Exception.ResourceNotFoundException;
import com.example.project.Repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;


@Service
public class ImageServiceImpl implements ImageService {

    private static final String URL = "cloudinary://944581129296765:ocCaycWvSGkZrjmXftyyNMgQuUc@it-academy-gang/";

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Image createImage(MultipartFile multipartFile) {
        Image image = new Image();

        File file;

        try {
            file = Files.createTempFile(System.currentTimeMillis() + "",
                    multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().length() - 4)).toFile();
            multipartFile.transferTo(file);

            Cloudinary cloudinary = new Cloudinary(URL);
            Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            image.setName((String) uploadResult.get("public_id"));
            image.setUrl((String) uploadResult.get("url"));
            image.setFormat((String) uploadResult.get("format"));

            return imageRepository.save(image);

        } catch (IOException e) {
            System.out.println("ImageService.createImage: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteByName(String name) {
        try{
            Cloudinary cloudinary = new Cloudinary(URL);
            cloudinary.uploader().destroy(name, ObjectUtils.emptyMap());
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Image> getAll() {
        return imageRepository.findAll();
    }

    @Override
    public Image getById(Long id) throws ResourceNotFoundException {
        return imageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Couldn't find image with id", id));
    }
}
