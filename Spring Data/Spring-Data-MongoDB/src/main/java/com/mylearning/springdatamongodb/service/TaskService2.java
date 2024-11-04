package com.mylearning.springdatamongodb.service;

import com.mylearning.springdatamongodb.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService2 {
// just for learning purpose haven't used anywhere in my code :)
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    public List<Task> findTasksByRawQuery(String assignee) {
//        // Create a raw query to find tasks by assignee
//        Query query = new Query();
//        query.addCriteria(Criteria.where("assignee").is(assignee));
//
//        return mongoTemplate.find(query, Task.class);
//    }
}
