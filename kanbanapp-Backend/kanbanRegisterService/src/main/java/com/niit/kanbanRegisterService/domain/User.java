package com.niit.kanbanRegisterService.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User
{
    @Id
    private String emailid;
    private String password;
    private String empname;
    private String designation;
    //private List<String> projects;
    // store project info
}
