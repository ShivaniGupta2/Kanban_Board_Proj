package com.niit.kanbanRegisterService.service;


import com.niit.RabbitMQ.domain.UserDTO;
import com.niit.kanbanRegisterService.config.Producer;
import com.niit.kanbanRegisterService.domain.User;

import com.niit.kanbanRegisterService.exception.UserAlreadyExits;
import com.niit.kanbanRegisterService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Service
public class UserServiceImpl implements UserService
{

    String otp;
    String emailid;
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;

    @Autowired
    Producer producer;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) throws UserAlreadyExits
    {

    User user1 = userRepository.findByEmailid(user.getEmailid());
    if(user1 == null) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmailid(user.getEmailid());
        userDTO.setPassword(user.getPassword());
        producer.sendMessageToRabbitMq(userDTO);
        emailService.sendRegistrationEmail(user.getEmailid());
        return userRepository.save(user);
    }
    else
        throw new UserAlreadyExits();
    }

    @Override
    public List<String > empList() {
        List<String>userList = new ArrayList<>();
        List<User> user = userRepository.findAll();
        ListIterator<User> userListIterator = user.listIterator();
        while(userListIterator.hasNext()){

            User user1 = userListIterator.next();

            userList.add(user1.getEmailid());
        }
        return userList;
    }

    @Override
    public User searchByEmailid(String emailid){
        User user = userRepository.findByEmailid(emailid);
        return user;
    }

    @Override
    public User updatePassword(String otp, String emailid, String password) {
        User user = userRepository.findByEmailid(emailid);
        if(otp.equals(getOtp())&& emailid.equals(getEmail())){
            user.setPassword(password);
        }
        System.out.println(user);
        userRepository.save(user);
        UserDTO userDTO = new UserDTO();
        userDTO.setEmailid(user.getEmailid());
        userDTO.setPassword(user.getPassword());
        producer.sendMessageToRabbitMq(userDTO);
        return null;
    }



    @Override
    public String getOtp() {
        return otp;
    }

    @Override
    public String getEmail() {
        return emailid;
    }

    @Override
    public void setOtpAndEmailid(String otpEmailService, String emailidEmailService) {
        otp = otpEmailService;
        emailid = emailidEmailService;
    }

}
