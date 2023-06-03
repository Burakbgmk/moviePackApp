package com.bluezz.moviepack.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private Double rating;

    private Integer rateCount;

    private LocalDate releaseDate;


    @ManyToOne
    private AppUser user;

    @OneToOne
    @JoinColumn(name = "photo_id")
    private Photo photo;

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }


    public Movie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public int getRateCount() {
        return rateCount;
    }

    public void setRateCount(int rateCount) {
        this.rateCount = rateCount;
    }
}
