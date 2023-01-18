package com.example.KanbanMongoService.service;

import com.example.KanbanMongoService.domain.Project;
import com.example.KanbanMongoService.domain.Tasks;
import com.example.KanbanMongoService.exception.MemberAlreadyExistException;
import com.example.KanbanMongoService.exception.ProjectAlreadyExistsException;
import com.example.KanbanMongoService.exception.ProjectNotFoundException;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProjectService {

   Project saveProject(Project project) throws ProjectAlreadyExistsException;
   Project findByProjectName(String projectname) ;

   List<Project> getall();

   List<String> projectNames(String emailid);

   //deleteProject -- only author can delete
   public String  deleteProject(String projectName) throws ProjectNotFoundException;

   Project addMembers(String projectName, String emailid) throws ProjectNotFoundException, MemberAlreadyExistException;


   public String deleteMember(String projectName, String emailid) throws ProjectNotFoundException;



}
