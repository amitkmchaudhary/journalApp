package com.journalpp.journalApp.controller;

import com.journalpp.journalApp.ap.response.WeatherResponse;
import com.journalpp.journalApp.entity.JournalEntry;
import com.journalpp.journalApp.entity.User;
import com.journalpp.journalApp.repository.UserRepository;
import com.journalpp.journalApp.service.JournalEntryService;
import com.journalpp.journalApp.service.UserService;
import com.journalpp.journalApp.service.WeatherService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private WeatherResponse weatherResponse;



    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userdb = userService.FindByUserName(userName);
        userdb.setUsername(user.getUsername());
        userdb.setPassword(user.getPassword());
        userService.saveNewUser(userdb);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deletuser(String user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping
    public ResponseEntity<?> greeting(String user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user1 = authentication.getName();
        WeatherResponse weatherResponse = weatherService.getWeather("Mumbai");
        String greeting = "";
        if (weatherResponse != null){
            greeting = " weather feels like " + weatherResponse.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("Hi "+user1+greeting,HttpStatus.OK);

    }





  }
