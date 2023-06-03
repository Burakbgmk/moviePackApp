package com.bluezz.moviepack.repository;

import com.bluezz.moviepack.entity.Movie;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MovieRepository extends CrudRepository<Movie,Long> {

}
