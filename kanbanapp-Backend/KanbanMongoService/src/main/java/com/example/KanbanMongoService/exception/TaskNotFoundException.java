package com.example.KanbanMongoService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Task Doesn't Exists")
public class TaskNotFoundException extends Exception{
}
