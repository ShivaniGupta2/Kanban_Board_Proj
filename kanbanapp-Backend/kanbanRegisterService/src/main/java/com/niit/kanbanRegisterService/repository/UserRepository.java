package com.niit.kanbanRegisterService.repository;

import com.niit.kanbanRegisterService.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
public interface UserRepository  extends MongoRepository<User,String>{

    User findByEmailid(String email);

}
