package com.example.RabbiitMQ.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsersDTO {
    private String emailid;
    private String password;
    private String project1;
    private String project2;
}
