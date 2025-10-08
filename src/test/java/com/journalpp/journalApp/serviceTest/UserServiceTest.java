package com.journalpp.journalApp.serviceTest;

import com.journalpp.journalApp.entity.User;
import com.journalpp.journalApp.repository.UserRepository;
import com.journalpp.journalApp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
 @Autowired
 private UserRepository userRepository;
    @Test
    public void testFindByUserName(){
        assertEquals(4  ,2+2);
        User user = userRepository.findByUsername("Ram1");
        assertTrue(!user.getJournalEntries().isEmpty());
    }
}
