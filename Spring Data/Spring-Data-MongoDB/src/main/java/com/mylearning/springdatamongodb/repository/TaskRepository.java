package com.mylearning.springdatamongodb.repository;

import com.mylearning.springdatamongodb.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {
    List<Task> findByTaskStatus(String status);

    List<Task> findByTaskPriority(Integer priority);

    List<Task> findByTaskAssignee(String assignee);

    List<Task> findByTaskAssigneeAndTaskPriority(String taskAssignee, Integer taskPriority);

    @Query("{ 'status': 'active', 'priority': { $gt: ?0 } }")
    List<Task> findActiveTasksWithPriorityGreaterThan(Integer priority);

    @Query("{ 'type': ?0 }")
    List<Task> findByTaskType(String type);

    @Query("{ 'name': { $regex: ?0, $options: 'i' } }")
    List<Task> findByNameContainingIgnoreCase(String name);

    //Return a list of Task objects (or a projection of them) that only includes the description and storyPoint fields.
    @Query(value = "{ assignee: ?0 ,priority: ?1}", fields = "{'description' : 1 , 'storyPoint': 2}")
    List<Task> findTaskWithAssigneeAndPriority(String assignee, String priority);

//    [
//    {
//        "description": "Description for Task 1",
//            "storyPoint": 5
//    },
//    {
//        "description": "Description for Task 3",
//            "storyPoint": 8
//    }
//    ]
}

