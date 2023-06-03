package com.bluezz.moviepack.repository;

import com.bluezz.moviepack.entity.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<AppUser,Long> {

    AppUser findByUsername(String username);
}
