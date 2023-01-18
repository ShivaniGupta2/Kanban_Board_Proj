package com.example.userauthenticationservice.service;

import com.example.userauthenticationservice.domain.Users;
import com.example.userauthenticationservice.exception.UserNotFoundException;
import com.example.userauthenticationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;

    }

    @Override
    public Users findByEmailAndPassword(String email, String password) throws UserNotFoundException {
        Users users =  userRepository.findByEmailidAndPassword(email,password);
        if(users == null){
            throw new UserNotFoundException();
        }
        return users;
    }

    @Override
    public Users saveUser(Users users) {
        return userRepository.save(users);
    }


}
