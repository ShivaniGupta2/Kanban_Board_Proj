package com.example.KanbanMongoService.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Project {
    @Id
    private String projectname;
    private String author;
    private List<Members> members;
    private List<Column> columnList;
}
// set email in local storage
//form fields ts - project name, project = localstorage.getproject()
// send everything
