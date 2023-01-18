package com.example.KanbanMongoService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.ALREADY_REPORTED, reason = "Column Already Exists")
public class ColumnAlreadyExistException extends Exception{

}
