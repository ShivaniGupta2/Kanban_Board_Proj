package com.niit.kanbanRegisterService.service;

import com.niit.kanbanRegisterService.domain.User;
import com.niit.kanbanRegisterService.exception.UserAlreadyExits;

import java.util.List;

public interface UserService
{
     User saveUser(User user) throws UserAlreadyExits;
      List<String> empList();
    public User searchByEmailid(String emailid);

    public User updatePassword(String otp, String emailid, String password);

    public String getOtp();
    public String getEmail();
    public void setOtpAndEmailid(String otp, String emailid);




// otp received, emailid password
    // get details by emailid
    // set new password
    // save
   // update user that will not throw any exception
}
