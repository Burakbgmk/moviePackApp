package com.bluezz.moviepack.service;

import com.bluezz.moviepack.entity.Photo;
import com.bluezz.moviepack.repository.PhotoRepository;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {
    private final PhotoRepository photoRepository;

    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }


    public Iterable<Photo> get() {
        return photoRepository.findAll();
    }

    public Photo get(Long id) {
        return photoRepository.findById(id).orElse(null);
    }


    public void remove(Long id) {
        photoRepository.deleteById(id);
    }

    public Photo save(String filename, String contentType, byte[] data) {
        Photo photo = new Photo();
        photo.setContentType(contentType);
        photo.setFileName(filename);
        photo.setData(data);
        photoRepository.save(photo);
        return photo;
    }


}
