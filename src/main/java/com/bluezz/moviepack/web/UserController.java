package com.bluezz.moviepack.web;

import com.bluezz.moviepack.entity.AppUser;
import com.bluezz.moviepack.security.UserPrinciple;
import com.bluezz.moviepack.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public ResponseEntity<String> login(@AuthenticationPrincipal UserPrinciple principle) {
        if(principle == null) {
            return new ResponseEntity<String>("0",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(principle.getUserId().toString(),HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public AppUser get(@PathVariable Long id) {
        Optional<AppUser> user = userService.findById(id);
        if(!user.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return user.get();

    }

//    @GetMapping("/admin")
//    public String admin(@AuthenticationPrincipal UserPrinciple principle) {
//        return "you are an admin! " + principle.getUsername()
//                + "User ID " + principle.getUserId();
//    }

    @PostMapping("/signup")
    public AppUser signup(@RequestBody AppUser user) {
        AppUser appUser = userService.save(user.getUsername(),user.getPassword());
        return appUser;
    }





}
