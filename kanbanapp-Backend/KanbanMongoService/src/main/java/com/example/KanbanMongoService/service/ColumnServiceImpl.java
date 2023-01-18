package com.example.KanbanMongoService.service;

import com.example.KanbanMongoService.domain.Column;
import com.example.KanbanMongoService.domain.Project;
import com.example.KanbanMongoService.domain.Tasks;
import com.example.KanbanMongoService.exception.ColumnAlreadyExistException;
import com.example.KanbanMongoService.exception.ColumnNotFoundException;
import com.example.KanbanMongoService.exception.ProjectNotFoundException;
import com.example.KanbanMongoService.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
@Service
public class ColumnServiceImpl implements ColumnService {

    private ProjectRepository projectRepository;
    @Autowired
    public ColumnServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }



    @Override
    public Project addColumn(String projectName, String colName) throws ProjectNotFoundException, ColumnAlreadyExistException {
        // match project name
        // update column

        Project project1 = projectRepository.findByProjectname(projectName);
        if(project1 == null)
            throw new ProjectNotFoundException();
        else{
            List<Column> col = project1.getColumnList();
            // extract all cols
            // then added new column
            // saved
            ListIterator<Column> columnListIterator = col.listIterator();
            while (columnListIterator.hasNext()){
                Column column = columnListIterator.next();
                if(column.getColName().equals(colName)) {
                    throw new ColumnAlreadyExistException();
                }
            }
            Column column = new Column();
            column.setColName(colName);
            column.setTasksList(new ArrayList<Tasks>());
            System.out.println(colName);
            System.out.println(column);
            col.add(column);
            project1.setColumnList(col);
            return projectRepository.save(project1);
        }

    }

    @Override
    public String deleteColumn(String projectname, String colname) throws ColumnNotFoundException, ProjectNotFoundException {
        Column column;
        boolean found= false;
        Project project = projectRepository.findByProjectname(projectname);
        if(project == null)
            throw new ProjectNotFoundException();
        else {
            List<Column> columnList = project.getColumnList();
            int index = 0;
            ListIterator<Column> columnListIterator = columnList.listIterator();
            while (columnListIterator.hasNext()) {
                column = columnListIterator.next();
                if (column.getColName().equals(colname)) {
                    index = columnList.indexOf(column);
                    found = true;
                }
            }
            if (!found){
                throw new ColumnNotFoundException();
            }
            columnList.remove(index);
            project.setColumnList(columnList);
            projectRepository.save(project);
        }
        return "Column deleted";
    }
}
