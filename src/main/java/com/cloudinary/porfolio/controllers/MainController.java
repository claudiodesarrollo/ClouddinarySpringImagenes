package com.cloudinary.porfolio.controllers;


import com.cloudinary.porfolio.dto.Mensaje;
import com.cloudinary.porfolio.services.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/cloudinary")
@CrossOrigin
public class MainController {
    @Autowired
    CloudinaryService cloudinaryService;

    @PostMapping("/upload")
    public ResponseEntity<Map> upload(@RequestParam MultipartFile multipartFile)throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if(bi == null){
            return new ResponseEntity(new Mensaje("imagen no válida"), HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id)throws IOException {
        Map result = cloudinaryService.delete(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }

}
