package com.bluezz.moviepack.service;

import com.bluezz.moviepack.entity.AppUser;
import com.bluezz.moviepack.entity.Movie;
import com.bluezz.moviepack.repository.MovieRepository;
import com.bluezz.moviepack.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final PhotoRepository photoRepository;

    public MovieService(MovieRepository movieRepository,
                        PhotoRepository photoRepository) {
        this.movieRepository = movieRepository;
        this.photoRepository = photoRepository;
    }

    public Iterable<Movie> get() {
        return movieRepository.findAll();
    }

    public Movie get(Long id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        return movie;
    }

    public void remove(Long id) {
        movieRepository.deleteById(id);
    }

    public Movie save(String name, String description, LocalDate releaseDate, AppUser user, Long photoId) {
        Movie movie = new Movie();
        movie.setName(name);
        movie.setDescription(description);
        movie.setReleaseDate(releaseDate);
        movie.setUser(user);
        movie.setRateCount(0);
        movie.setPhoto(photoRepository.findById(photoId).get());
        movieRepository.save(movie);
        return movie;
    }

    public Movie save(Long id, Double rating, Integer rateCount) {
        Movie movie = movieRepository.findById(id).get();
        movie.setName(movie.getName());
        movie.setDescription(movie.getDescription());
        movie.setReleaseDate(movie.getReleaseDate());
        movie.setUser(movie.getUser());
        movie.setRateCount(rateCount);
        movie.setRating(rating);
        movieRepository.save(movie);
        return movie;
    }
}
