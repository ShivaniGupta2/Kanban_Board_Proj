package com.example.KanbanMongoService.service;

import com.example.KanbanMongoService.domain.Project;
import com.example.KanbanMongoService.domain.Tasks;
import com.example.KanbanMongoService.exception.TaskAlreadyExistException;
import com.example.KanbanMongoService.exception.TaskNotFoundException;

public interface TaskService {

    Project addTask(String projectName, String colName, Tasks tasks) throws TaskAlreadyExistException ;
    Boolean findTask(String projectName,Tasks tasks);

    Boolean movetask(String projectName, String prevcol, String nextcol, Tasks task) throws TaskAlreadyExistException, TaskNotFoundException;
    Boolean deleteTask(String projectName, String col,  Tasks task) throws TaskNotFoundException;
    Boolean editTask(String projectName, String col,  Tasks task) throws TaskNotFoundException;
// make delete task
}
