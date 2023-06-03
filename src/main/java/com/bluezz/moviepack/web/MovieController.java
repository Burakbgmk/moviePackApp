package com.bluezz.moviepack.web;

import com.bluezz.moviepack.dto.MovieDto;
import com.bluezz.moviepack.dto.MovieRateDto;
import com.bluezz.moviepack.entity.AppUser;
import com.bluezz.moviepack.entity.Movie;
import com.bluezz.moviepack.entity.Photo;
import com.bluezz.moviepack.service.MovieService;
import com.bluezz.moviepack.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class MovieController {

    private final MovieService movieService;
    private final UserService userService;

    public MovieController(MovieService movieService, UserService userService) {
        this.movieService = movieService;
        this.userService = userService;
    }


    @GetMapping("/movie")
    public List<MovieDto> get() {
        Iterable<Movie> movies = movieService.get();
        List<MovieDto> movieDtos = new ArrayList<>();
        for(Movie movie : movies) {
            MovieDto dto = new MovieDto();
            dto.setId(movie.getId());
            dto.setUserId(movie.getUser().getId());
            dto.setName(movie.getName());
            dto.setDescription(movie.getDescription());
            dto.setReleaseDate(movie.getReleaseDate());
            dto.setRating(movie.getRating());
            dto.setPhotoId(movie.getPhoto().getId());
            movieDtos.add(dto);
        }
        return movieDtos;
    }

    @GetMapping("/movie/{id}")
    public Movie get(@PathVariable Long id) {
        Movie movie = movieService.get(id);
        if(movie == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return movie;
    }
    @DeleteMapping("/movie/{id}")
    public void delete(@PathVariable Long id) {
        movieService.remove(id);
    }

    @PostMapping("/movie")
    public Movie create(@RequestBody MovieDto dto) throws IOException {
        AppUser user = userService.findById(dto.getUserId()).get();
        Movie movie = movieService.save(dto.getName(),dto.getDescription(), LocalDate.now(),user, dto.getPhotoId());
        List<Movie> movieList = new ArrayList<>();
        movieService.get().forEach(movieList::add);
        movieList.add(movie);
        user.setMovies(movieList);
        return movie;
    }

    @PostMapping("/movie/rate")
    public Double rate(@RequestBody MovieRateDto dto) {
        Movie movie = movieService.get(dto.getId());
        Double currentRating = movie.getRating();
        Integer currentRateCount = movie.getRateCount();
        Double finalRating;
        Integer finalCount;
        if(currentRating == null){
            finalRating = dto.getRate();
            finalCount = currentRateCount + 1;
        }
        else{
            finalCount = currentRateCount + 1;
            finalRating = ((currentRating * currentRateCount) + dto.getRate())/(finalCount);
        }
        movieService.save(movie.getId()
                ,finalRating
                ,finalCount);



        return finalRating;

    }

    @GetMapping("/movie/rate/{id}")
    public Double getRate(@PathVariable Long id) {
        Double rate = movieService.get(id).getRating();
        return rate;
    }
}
