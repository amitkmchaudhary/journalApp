package com.journalpp.journalApp.controller;

import com.journalpp.journalApp.entity.JournalEntry;
import com.journalpp.journalApp.entity.User;
import com.journalpp.journalApp.repository.JournalEntryRepository;
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
@RequestMapping("journal")
public class JournalEntryControllerV2 {
    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;

    @GetMapping("{UserName}")
    public ResponseEntity<?> getAllJournalByUser(@PathVariable String UserName) {
        User user = userService.FindByUserName(UserName);
        List<JournalEntry> all = user.getJournalEntries();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }
    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> creatEntry(@RequestBody JournalEntry myEntry,@PathVariable String userName){
        try {
            journalEntryService.saveEntry(myEntry, userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }


    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getEnrtybyid(@PathVariable ObjectId myId){

        Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
        if (journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{username}/{myId}")
    public ResponseEntity<?> updateEnrtybyid(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry){
        JournalEntry old = journalEntryService.findById(myId).orElse(null);
        if(old!= null)
        {
            old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().isEmpty() ?newEntry.getTitle() : old.getTitle() );
            old.setContent(newEntry.getContent()!=null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


    @DeleteMapping("id/{username}/{myId}")
    public ResponseEntity<?> deleteEnrtybyid(@PathVariable ObjectId myId,@PathVariable String username){

             journalEntryService.deleteById(myId,username);
             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
