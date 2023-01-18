package com.niit.RabbitMQ.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String emailid;
    private String password;
    private String empname;
    private String designation;
}
