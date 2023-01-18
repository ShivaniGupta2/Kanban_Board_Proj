package com.example.KanbanMongoService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.ALREADY_REPORTED, reason = "Member Already Exists")
public class MemberAlreadyExistException extends Exception{
}
