package com.example.KanbanMongoService.service;

import com.example.KanbanMongoService.controller.ProjectController;
import com.example.KanbanMongoService.domain.Column;
import com.example.KanbanMongoService.domain.Project;
import com.example.KanbanMongoService.domain.Tasks;
import com.example.KanbanMongoService.exception.TaskAlreadyExistException;
import com.example.KanbanMongoService.exception.TaskNotFoundException;
import com.example.KanbanMongoService.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Service
public class TaskServiceImpl implements TaskService{

    private ProjectRepository projectRepository;
    TaskServiceImpl taskServiceImpl;

    @Autowired
    public TaskServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project addTask(String projectName, String colName, Tasks tasks) throws TaskAlreadyExistException
    {
        System.out.println(findTask(projectName, tasks));
        if(!findTask(projectName, tasks)){
            int index;
            Column col2;
            List<Tasks> tk = null;
            Project project1 = projectRepository.findByProjectname(projectName);

            List<Column> col = project1.getColumnList();
            //System.out.println(col);
            // match the col with colname
            ListIterator<Column> iterator = col.listIterator();
            while (iterator.hasNext()) {
                col2 = iterator.next();
                tk = col2.getTasksList();
                if (col2.getColName().equals(colName)) {
                    index = col.indexOf(col2);
                    tk.add(tasks);
                    col2.setTasksList(tk);
                    System.out.println(col2);
                    //col.remove(index);
                    col.set(index, col2);
                }
            }

            project1.setColumnList(col);
            return projectRepository.save(project1);}
            else
                throw new TaskAlreadyExistException();

    }

    @Override
    public Boolean findTask(String projectName,Tasks tasks) {
        Boolean exists=false;
        Column column;
        Tasks tasks1;
        Project project = projectRepository.findByProjectname(projectName);
        List<Column> columnList = project.getColumnList();

        for (Column value : columnList) {
            column = value;
            List<Tasks> tasksList = column.getTasksList();
            if(tasksList ==null)
                return false;
            for (Tasks item : tasksList) {
                tasks1 = item;
                if (tasks1.getHeading().equals(tasks.getHeading())) {
                    exists = true;
                }
            }
        }
        return exists;
    }

    @Override
    public Boolean movetask(String projectName, String prevcol, String nextcol, Tasks task) throws TaskAlreadyExistException, TaskNotFoundException {
        System.out.println(projectName);
        System.out.println(prevcol);
        System.out.println(nextcol);
        System.out.println(task);
        if(deleteTask(projectName, prevcol, task)){
            addTask(projectName, nextcol, task);
            return true;
        }
        else
            return false;
    }

    @Override
    public Boolean deleteTask(String projectName, String col, Tasks task)throws TaskNotFoundException
    {
        Boolean deleted = false;
        Project project = projectRepository.findByProjectname(projectName);
        List<Column> columnList = project.getColumnList();
        ListIterator<Column> columnListIterator = columnList.listIterator();
        Column col1;

        Tasks tk;
        int index =0;
        int i =0; //column index
// find the col and check if task exist
// delete it
// use above function then to store
        while(columnListIterator.hasNext()){
            col1 = columnListIterator.next();
            if(col1.getColName().equals(col)){
                List<Tasks> tasksList = col1.getTasksList();
                for (Tasks tasks : tasksList) {
                    tk = tasks;
                    if (tk.getHeading().equals(task.getHeading()) &&
                            tk.getDescription().equals(task.getDescription()) &&
                            tk.getDeadline().equals(task.getDeadline()) &&
                            tk.getPriority().equals(task.getPriority()) &&
                            tk.getAssignedto().equals(task.getAssignedto())) {

                        index = tasksList.indexOf(tk);
                        tasksList.remove(index);//delete
                        //replace
                        //tasksList.set(index, task);// replaced old with new

                        col1.setTasksList(tasksList);
                        columnList.set(i,col1);
                        deleted = true;
                        project.setColumnList(columnList);
                        projectRepository.save(project);
                        break;
                    }

                }
                break;
            }

            i++;
        }
        if(!deleted)
            throw new TaskNotFoundException();
        else
            return deleted;
    }

    @Override
    public Boolean editTask(String projectName, String col, Tasks task) throws TaskNotFoundException {

        Boolean edited = false;
        Project project = projectRepository.findByProjectname(projectName);
        List<Column> columnList = project.getColumnList();
        ListIterator<Column> columnListIterator = columnList.listIterator();
        Column col1;

        Tasks tk;
        int index =0;
        int i =0; //column index
// find the col and check if task exist
// delete it
// use above function then to store
        while(columnListIterator.hasNext()){
            col1 = columnListIterator.next();
            if(col1.getColName().equals(col)){
                List<Tasks> tasksList = col1.getTasksList();
                for (Tasks tasks : tasksList) {
                    tk = tasks;
                    if (tk.getHeading().equals(task.getHeading())){

                        index = tasksList.indexOf(tk);
                        //tasksList.remove(index);//delete
                        //replace
                        tasksList.set(index, task);// replaced old with new

                        col1.setTasksList(tasksList);
                        columnList.set(i,col1);
                        edited = true;
                        project.setColumnList(columnList);
                        projectRepository.save(project);
                        break;
                    }

                }
                break;
            }

            i++;
        }
        if(!edited)
            throw new TaskNotFoundException();
        else
            return edited;
    }
}
