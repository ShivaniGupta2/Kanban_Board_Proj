package com.example.userauthenticationservice.controller;


import com.example.userauthenticationservice.domain.Users;
import com.example.userauthenticationservice.service.UserService;
import com.example.userauthenticationservice.exception.UserNotFoundException;
import com.example.userauthenticationservice.service.SecurityTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("kanban/v3")
public class UserController {

    private ResponseEntity responseEntity;
    private UserService userService;
    private SecurityTokenGenerator securityTokenGenerator;


    @Autowired
    public UserController(UserService userService , SecurityTokenGenerator securityTokenGenerator)
    {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }


    //Should only give username and password
    @PostMapping("/loginapi")
    public ResponseEntity loginUser(@RequestBody Users users) throws UserNotFoundException {

        Map<String, String> map = null;
        try {
            Users usersObj = userService.findByEmailAndPassword(users.getEmailid(), users.getPassword());
            if (usersObj.getEmailid().equals(users.getEmailid())) {
                map = securityTokenGenerator.generateToken(users);
            }
            responseEntity = new ResponseEntity(map, HttpStatus.OK);
        }
        catch(UserNotFoundException e){
            throw new UserNotFoundException();
        }
        catch (Exception e){
            responseEntity = new ResponseEntity("Try after sometime!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }




}
