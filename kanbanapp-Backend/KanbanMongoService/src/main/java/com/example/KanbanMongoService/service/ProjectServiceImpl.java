package com.example.KanbanMongoService.service;

import com.example.KanbanMongoService.domain.Column;
import com.example.KanbanMongoService.domain.Members;
import com.example.KanbanMongoService.domain.Project;
import com.example.KanbanMongoService.domain.Tasks;
import com.example.KanbanMongoService.exception.ColumnAlreadyExistException;
import com.example.KanbanMongoService.exception.MemberAlreadyExistException;
import com.example.KanbanMongoService.exception.ProjectAlreadyExistsException;
import com.example.KanbanMongoService.exception.ProjectNotFoundException;
import com.example.KanbanMongoService.repository.ProjectRepository;
import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Service
public class ProjectServiceImpl implements ProjectService{


    private ProjectRepository projectRepository;
    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project saveProject(Project project) throws ProjectAlreadyExistsException {
        Project project1 = findByProjectName(project.getProjectname());
        if(project1 == null)
            return projectRepository.save(project);
        else throw new ProjectAlreadyExistsException();
    }

    @Override
    public Project findByProjectName(String projectname) {
        Project project = projectRepository.findByProjectname(projectname);
//        if(project == null)
//            throw new ProjectNotFoundException();
//        else
            return project;

    }

    // extract all emails





    @Override
    public List<Project> getall() {
        return projectRepository.findAll();
    }

    @Override
    public List<String> projectNames(String emailid) {
        List<String> projectNames = new ArrayList<>();
        List<Project> projectList = getall();

        for (Project project : projectList) {
            List<Members> members = project.getMembers();

            for (Members members1 : members) {
                if (members1.getEmailid().equals(emailid))
                    projectNames.add(project.getProjectname());
            }
        }
        return projectNames;
    }

    @Override
    public Project addMembers(String projectName, String emailid) throws ProjectNotFoundException,MemberAlreadyExistException {
        Project project = findByProjectName(projectName);
        List<Members> members = project.getMembers();
        if (project == null){
            throw new ProjectNotFoundException();
        }
        else{

            ListIterator<Members> membersListIterator= members.listIterator();
            while (membersListIterator.hasNext()){
                Members members1 = membersListIterator.next();
                if(members1.getEmailid().equals(emailid)) {
                    throw new MemberAlreadyExistException();
                }
            }
            Members members1 = new Members();
            members1.setName("");
            members1.setEmailid(emailid);
            System.out.println(members1);
            System.out.println(emailid);
            members.add(members1);
            project.setMembers(members);
            return projectRepository.save(project);
        }
    }

    @Override
    public String deleteMember(String projectName, String emailid) throws ProjectNotFoundException {
        Project project = findByProjectName(projectName);
        List<Members> members = project.getMembers();
        Members members1;
        if (project == null){
            throw new ProjectNotFoundException();
        }
        else{
            ListIterator<Members> listIterator  = members.listIterator();
            while(listIterator.hasNext()){
                members1 = listIterator.next();
                if(members1.getEmailid().equals(emailid)){
                    listIterator.remove();
                    project.setMembers(members);
                    projectRepository.save(project);
                    return "Member Removed";
                }
            }
        }
        return "Member not Found";
        }


    @Override
    public String  deleteProject(String projectName) throws ProjectNotFoundException {
        if (projectRepository.findByProjectname(projectName) == null) {
            throw new ProjectNotFoundException();
        }
        else {
            projectRepository.deleteByProjectname(projectName);
        }
        return "Deleted Successfully";
    }



}
