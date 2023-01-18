package com.example.userauthenticationservice.config;


import com.example.RabbiitMQ.domain.UsersDTO;

import com.example.userauthenticationservice.domain.Users;
import com.example.userauthenticationservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Component
public class Consumer {
    @Autowired
    UserServiceImpl userService;

    @RabbitListener(queues = "kanban_queue")
    public void getDataFromRabbitMq(UsersDTO usersDTO) {
        Users users = new Users();
        users.setEmailid(usersDTO.getEmailid());
        System.out.println("emails-  "+usersDTO.getEmailid());
        users.setPassword(usersDTO.getPassword());
        System.out.println("password---  "+usersDTO.getPassword());
        userService.saveUser(users);
    }

}
