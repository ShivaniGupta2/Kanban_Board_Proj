package com.example.KanbanMongoService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.ALREADY_REPORTED, reason = "Project Already Exists")
public class ProjectAlreadyExistsException extends Exception {
}
