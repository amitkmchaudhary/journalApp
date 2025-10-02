package com.journalpp.journalApp.controller;

import com.journalpp.journalApp.entity.JournalEntry;
import com.journalpp.journalApp.entity.User;
import com.journalpp.journalApp.repository.UserRepository;
import com.journalpp.journalApp.service.JournalEntryService;
import com.journalpp.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
//        String user = authentication.getName();
        userRepository.deleteByUsername(authentication.getName());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }





  }
