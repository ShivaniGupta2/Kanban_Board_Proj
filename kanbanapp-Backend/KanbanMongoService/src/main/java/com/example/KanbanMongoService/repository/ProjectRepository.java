package com.example.KanbanMongoService.repository;

import com.example.KanbanMongoService.domain.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@EnableMongoRepositories
public interface ProjectRepository extends MongoRepository<Project, Integer> {


     Project findByProjectname(String projectname);
     String deleteByProjectname(String projectName);


}
