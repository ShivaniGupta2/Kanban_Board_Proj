package com.example.userauthenticationservice.service;

import com.example.userauthenticationservice.domain.Users;

import java.util.Map;

public interface SecurityTokenGenerator {
    Map<String,String> generateToken(Users users);
}
