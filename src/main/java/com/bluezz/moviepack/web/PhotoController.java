package com.bluezz.moviepack.web;

import com.bluezz.moviepack.entity.Photo;
import com.bluezz.moviepack.service.PhotoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class PhotoController {

    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/photoz")
    public Iterable<Photo> get() {
        return photoService.get();
    }

    @GetMapping("/photoz/{id}")
    public Photo get(@PathVariable Long id) {
        Photo photo = photoService.get(id);
        if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photo;
    }
    @DeleteMapping("/photoz/{id}")
    public void delete(@PathVariable Long id) {
        photoService.remove(id);
    }

    @PostMapping("/photoz")
    public Photo create(@RequestPart("data") MultipartFile file) throws IOException {
        Photo photo = photoService.save(file.getOriginalFilename(), file.getContentType(), file.getBytes());
        return photo;
    }
}
