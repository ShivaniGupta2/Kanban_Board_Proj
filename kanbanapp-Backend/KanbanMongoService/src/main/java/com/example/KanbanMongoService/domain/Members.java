package com.example.KanbanMongoService.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Members {
    private String name;
    private String emailid;
    // no of task
}
