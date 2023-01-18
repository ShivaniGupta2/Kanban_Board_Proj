package com.example.userauthenticationservice.service;

import com.example.userauthenticationservice.domain.Users;
import com.example.userauthenticationservice.exception.UserNotFoundException;

import java.util.List;

public interface UserService {

    public Users findByEmailAndPassword(String email , String password) throws UserNotFoundException;
     public Users saveUser(Users users);

}
