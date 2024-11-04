package com.mylearning.springdatamongodb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Task")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Task {
    @Id
    @Field("id")
    private String taskId;
    @Field("name")
    private String taskName;
    @Field("description")
    private String taskDescription;
    @Field("status")
    private String taskStatus; //active or inactive
    @Field("priority")
    private Integer taskPriority; // 1, 2 and 3
    @Field("type")
    private String taskType;
    private Integer storyPoint;
    @Field("assignee")
    private String taskAssignee;
}
