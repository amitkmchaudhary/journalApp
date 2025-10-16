package com.journalpp.journalApp.service;

import com.journalpp.journalApp.entity.JournalEntry;
import com.journalpp.journalApp.entity.User;
import com.journalpp.journalApp.repository.JournalEntryRepository;
import com.journalpp.journalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

     public void saveEntry(User user){
         userRepository.save(user);
     }


     public boolean saveNewUser(User user){
         try {
             user.setPassword(passwordEncoder.encode(user.getPassword()));
             user.setRoles(new ArrayList<>(List.of("USER")));
             userRepository.save(user);
             return true;
         } catch (Exception e) {
             log.error("error occurred for {}:",user.getUsername(),e);
             return false;
         }

         }

    public void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(new ArrayList<>(List.of("USER","ADMIN")));
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

     public User FindByUserName(String username){
         return userRepository.findByUsername(username);
     }
}
