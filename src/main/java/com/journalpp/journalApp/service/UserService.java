package com.journalpp.journalApp.service;

import com.journalpp.journalApp.entity.JournalEntry;
import com.journalpp.journalApp.entity.User;
import com.journalpp.journalApp.repository.JournalEntryRepository;
import com.journalpp.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

     public void saveEntry(User user){
         userRepository.save(user);
     }

     public List<User> getAll(){
        return userRepository.findAll();
     }

     public Optional<User> findById(ObjectId id){
            return userRepository.findById(id);
     }

     public void deleteById(ObjectId id){
         userRepository.deleteById(id);
     }

     public User FindByUsername(String username){
         return userRepository.findByUsername(username);
     }
}
