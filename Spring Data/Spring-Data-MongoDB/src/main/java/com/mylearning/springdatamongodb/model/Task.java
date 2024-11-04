package com.mylearning.springdatamongodb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "Task_Table")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Task {
    private String taskId;
    private String taskName;
    private String taskDescription;
    private String taskStatus; //active or inactive
    private Integer taskPriority; // 1, 2 and 3
    private String taskType;
    private Integer storyPoint;
    private String taskAssignee;
}
