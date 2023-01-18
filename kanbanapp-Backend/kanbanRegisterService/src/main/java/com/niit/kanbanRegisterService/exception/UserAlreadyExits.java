package com.niit.kanbanRegisterService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.ALREADY_REPORTED,reason = "User Already Exists")
public class UserAlreadyExits extends Exception{
}
