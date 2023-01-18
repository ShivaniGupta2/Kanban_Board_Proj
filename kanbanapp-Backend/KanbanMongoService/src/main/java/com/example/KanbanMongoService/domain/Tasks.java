package com.example.KanbanMongoService.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tasks {
    @Id
    private String heading;
    private String description;
    private String deadline; //Date
    //private String status;
    private String priority; // color based on this
    private String assignedto; // lets add 2 members atmost
    //private String assignon;
    // members
    // heading, task name, desc, priority, card type,
}


