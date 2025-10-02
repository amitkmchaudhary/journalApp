package com.journalpp.journalApp.repository;


import com.journalpp.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

                User findByUsername(String username);
                void deleteByUsername(String username);
}
