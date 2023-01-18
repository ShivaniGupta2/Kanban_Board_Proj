package com.example.KanbanMongoService.service;

import com.example.KanbanMongoService.domain.Project;
import com.example.KanbanMongoService.exception.ColumnAlreadyExistException;
import com.example.KanbanMongoService.exception.ColumnNotFoundException;
import com.example.KanbanMongoService.exception.ProjectNotFoundException;

public interface ColumnService {
    Project addColumn(String projectName, String colName) throws ColumnAlreadyExistException, ProjectNotFoundException;

    String deleteColumn(String projectname, String colname) throws ColumnNotFoundException, ProjectNotFoundException;
}
