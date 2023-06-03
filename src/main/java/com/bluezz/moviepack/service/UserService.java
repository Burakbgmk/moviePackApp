package com.bluezz.moviepack.service;

import com.bluezz.moviepack.entity.AppUser;
import com.bluezz.moviepack.entity.Movie;
import com.bluezz.moviepack.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private static final String ADMIN_USERNAME = "admin";
    private static final String COMMON_USERNAME = "burak";

    public Optional<AppUser> findByUsername(String username) {
        AppUser appUser = userRepository.findByUsername(username);
        if(appUser == null) return Optional.empty();
        return Optional.of(appUser);

//        if(ADMIN_USERNAME.equalsIgnoreCase(username)) {
//            var user = new AppUser();
//            user.setId(1L);
//            user.setUsername(ADMIN_USERNAME);
//            user.setPassword("$2a$12$iMO6fwcY9C61lZVp4.4v1.Q6EjX4/.KfPXk0XpTE3SWCbpabtD9Zq");
//            user.setRole("ROLE_ADMIN");
//            return Optional.of(user);
//        } else if (COMMON_USERNAME.equalsIgnoreCase(username)) {
//            var user = new AppUser();
//            user.setId(99L);
//            user.setUsername(COMMON_USERNAME);
//            user.setPassword("$2a$12$iMO6fwcY9C61lZVp4.4v1.Q6EjX4/.KfPXk0XpTE3SWCbpabtD9Zq");
//            user.setRole("ROLE_USER");
//            return Optional.of(user);
//        }
//
//        return Optional.empty();

    }

    public Optional<AppUser> findById(Long id) {
        Optional<AppUser> appUser = userRepository.findById(id);
        if(appUser.isPresent()) {
            return appUser;
        }
        return Optional.empty();
    }

    public AppUser save(String username, String password) {
        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setPassword(new BCryptPasswordEncoder().encode(password));
        appUser.setRole("USER");
        userRepository.save(appUser);
        return appUser;
    }


}
