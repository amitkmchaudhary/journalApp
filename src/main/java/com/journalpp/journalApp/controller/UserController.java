package com.journalpp.journalApp.controller;

import com.journalpp.journalApp.entity.JournalEntry;
import com.journalpp.journalApp.entity.User;
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




    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userdb = userService.FindByUserName(userName);
        userdb.setUsername(user.getUsername());
        userdb.setPassword(user.getPassword());
        userService.saveEntry(userdb);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @DeleteMapping
//    public void deletuser(String user){
//        userService.de
//    }





  }
