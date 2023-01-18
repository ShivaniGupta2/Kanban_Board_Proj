package com.niit.kanbanRegisterService.controller;

import com.niit.kanbanRegisterService.domain.User;
import com.niit.kanbanRegisterService.exception.UserAlreadyExits;
import com.niit.kanbanRegisterService.service.EmailService;
import com.niit.kanbanRegisterService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kanban/v2")
public class UserController
{
    private UserService userService;
    private EmailService emailService;
    @Autowired
    public UserController(UserService userService, EmailService emailService)
    {
        this.userService = userService;
        this.emailService = emailService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyExits
    {
        try{
            return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
        }
        catch (UserAlreadyExits exp){
            return new ResponseEntity<>("User Already Exists", HttpStatus.ALREADY_REPORTED);
           // throw new UserAlreadyExits();
        }
    }
    @GetMapping("/emplist")
    public ResponseEntity<?> getEmpNames(){
        return new ResponseEntity<>(userService.empList(), HttpStatus.OK);
    }

    @GetMapping("/searchbyemailid/{emailid}")
    public ResponseEntity<?> getUserdata(@PathVariable String emailid){

        return new ResponseEntity<>(userService.searchByEmailid(emailid), HttpStatus.OK);
    }

    @GetMapping("otp/{emailid}")
    public ResponseEntity<?> sendOtp(@PathVariable String emailid){

        return new ResponseEntity<>(emailService.sendOtp(emailid), HttpStatus.OK);
    }

    @GetMapping("resetPassword/{otp}/{emailid}/{password}")
    public ResponseEntity<?> resetPassword(@PathVariable String otp , @PathVariable String emailid,@PathVariable String password){

        return new ResponseEntity<>(userService.updatePassword(otp, emailid,password), HttpStatus.OK);
    }

    @GetMapping("inviteemail/{emailid}")
    public ResponseEntity<?> inviteEmail( @PathVariable String emailid){

        return new ResponseEntity<>(emailService.sendInviteEmail(emailid), HttpStatus.OK);
    }
}
