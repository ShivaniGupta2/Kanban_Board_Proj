package com.example.KanbanMongoService.controller;

import com.example.KanbanMongoService.domain.Project;
import com.example.KanbanMongoService.domain.Tasks;
import com.example.KanbanMongoService.exception.*;
import com.example.KanbanMongoService.service.ColumnService;
import com.example.KanbanMongoService.service.ProjectService;
import com.example.KanbanMongoService.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("kanban/v1")
public class ProjectController {

    private ProjectService projectService;
    private ColumnService columnService;
    private TaskService taskService;
    @Autowired
    public ProjectController(ProjectService projectService, ColumnService columnService, TaskService taskService) {
        this.projectService = projectService;
        this.columnService = columnService;
        this.taskService = taskService;
    }



    @PostMapping("/save")
    public ResponseEntity<?> saveProject(@RequestBody Project project) throws  ProjectAlreadyExistsException {
        //return new ResponseEntity<>(projectService.saveProject(project), HttpStatus.CREATED);
        try{
            return new ResponseEntity<>(projectService.saveProject(project), HttpStatus.CREATED);
       } catch (ProjectAlreadyExistsException e) {
            return new ResponseEntity<>("Board Already Exists", HttpStatus.ALREADY_REPORTED);}

    }

    @GetMapping("getallprojects")
    public ResponseEntity<?> getallproject(){
        return new ResponseEntity<>(projectService.getall(), HttpStatus.ACCEPTED);
    }

    @GetMapping("findproject/{projectname}")
    public ResponseEntity<?> getProjectByEmail(@PathVariable String projectname)  {
        return new ResponseEntity<>(projectService.findByProjectName(projectname), HttpStatus.ACCEPTED);
    }
    @GetMapping("/addcol/{projectName}/{colName}")
    public ResponseEntity<?> addCol(@PathVariable String projectName, @PathVariable String colName) throws ProjectNotFoundException, ColumnAlreadyExistException {
        try {
            return new ResponseEntity<>(columnService.addColumn(projectName, colName), HttpStatus.ACCEPTED);
        }
        catch (ColumnAlreadyExistException ex){
            return new ResponseEntity<>("Column Already Exists", HttpStatus.ALREADY_REPORTED);
        }
    }

    @PostMapping("addtask/{projectName}/{colName}")
    public ResponseEntity<?> addTask(@RequestBody Tasks tasks, @PathVariable String projectName, @PathVariable String colName ) throws TaskAlreadyExistException {
        try {
            return new ResponseEntity<>(taskService.addTask(projectName, colName, tasks), HttpStatus.CREATED);
        }
        catch (TaskAlreadyExistException exception){
            return new ResponseEntity<>("Task Already Exists", HttpStatus.ALREADY_REPORTED);
        }
    }

    @PostMapping("movetask/{projectName}/{prevcol}/{nextcol}")
    public ResponseEntity<?> moveTask(@RequestBody Tasks tasks, @PathVariable String projectName,
                                      @PathVariable String prevcol,@PathVariable String nextcol ) throws TaskAlreadyExistException, TaskNotFoundException {
        taskService.movetask(projectName, prevcol, nextcol, tasks);
        return new ResponseEntity<>("task moved successfully", HttpStatus.CREATED);
    }
    @GetMapping("projectnames/{emailid}")
    public ResponseEntity<?> getProjectNames(@PathVariable String emailid){

        return new ResponseEntity<>(projectService.projectNames(emailid), HttpStatus.CREATED);
    }
    @DeleteMapping("deleteCol/{projectName}/{colName}")
    public ResponseEntity<?> deleteCol(@PathVariable String projectName,@PathVariable String colName) throws ColumnNotFoundException, ProjectNotFoundException {
        try {
            return new ResponseEntity<>(columnService.deleteColumn(projectName, colName), HttpStatus.OK);
        }
        catch (ColumnNotFoundException exception){
            return new ResponseEntity<>("Column Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("deletetask/{projectName}/{colName}")
    public ResponseEntity<?> deletetask(@PathVariable String projectName,@PathVariable String colName ,@RequestBody Tasks task) throws TaskNotFoundException {
            try {
                return new ResponseEntity<>(taskService.deleteTask(projectName, colName, task), HttpStatus.OK);
            }
            catch (TaskNotFoundException e){
                return new ResponseEntity<>("Task Not Found", HttpStatus.NOT_FOUND);
            }


    }

    @PostMapping("edittask/{projectName}/{colName}")
    public ResponseEntity<?> editTask(@PathVariable String projectName,@PathVariable String colName ,@RequestBody Tasks task) throws TaskNotFoundException {
        try {
            return new ResponseEntity<>(taskService.editTask(projectName, colName, task), HttpStatus.OK);
        }
        catch (TaskNotFoundException e){
            return new ResponseEntity<>("Task Not Found", HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("deleteProject/{projectName}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectName) throws ProjectNotFoundException {
        try {
            return new ResponseEntity<>(projectService.deleteProject(projectName), HttpStatus.OK);
        }
        catch (ProjectNotFoundException exception){
            return new ResponseEntity<>("Project Not Found", HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/addmember/{projectname}/{emailid}")
    public ResponseEntity<?> addMember(@PathVariable String projectname, @PathVariable String emailid) throws ProjectNotFoundException,MemberAlreadyExistException{
        try{
            return new ResponseEntity<>(projectService.addMembers(projectname,emailid), HttpStatus.ACCEPTED);
        }
        catch (MemberAlreadyExistException memberAlreadyExistException){
            return new ResponseEntity<>("Member Already Exists", HttpStatus.ALREADY_REPORTED);
        }
    }

    @GetMapping("/deletemember/{projectname}/{emailid}")
    public ResponseEntity<?> deleteMember(@PathVariable String projectname, @PathVariable String emailid) throws ProjectNotFoundException{
        try{
            return new ResponseEntity<>(projectService.deleteMember(projectname,emailid), HttpStatus.ACCEPTED);
        }
        catch (ProjectNotFoundException exception){
            return new ResponseEntity<>("Member Already Exists", HttpStatus.ALREADY_REPORTED);
        }
    }



}
