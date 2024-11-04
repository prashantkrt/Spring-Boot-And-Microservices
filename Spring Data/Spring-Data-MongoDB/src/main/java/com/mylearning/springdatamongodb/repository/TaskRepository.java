package com.mylearning.springdatamongodb.repository;

import com.mylearning.springdatamongodb.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {
}
