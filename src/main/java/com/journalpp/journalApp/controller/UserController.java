package com.journalpp.journalApp.controller;

import com.journalpp.journalApp.entity.JournalEntry;
import com.journalpp.journalApp.entity.User;
import com.journalpp.journalApp.service.JournalEntryService;
import com.journalpp.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping
    public List<User> getallUser(){
        return userService.getAll();
    }

    @PostMapping
    public void creatUser(@RequestBody User user){
         userService.saveEntry(user);
    }

    @PutMapping("/{UserName}")
    public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable String UserName){
        User userdb = userService.FindByUsername(UserName);
        if (userdb!=null){
            userdb.setUsername(user.getUsername());
            userdb.setPassword(user.getPassword());
            userService.saveEntry(userdb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @DeleteMapping
//    public void deletuser(String user){
//        userService.de
//    }





  }
