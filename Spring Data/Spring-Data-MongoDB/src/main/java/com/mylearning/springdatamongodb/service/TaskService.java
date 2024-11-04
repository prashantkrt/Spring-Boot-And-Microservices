package com.mylearning.springdatamongodb.service;

import com.mylearning.springdatamongodb.model.Task;
import com.mylearning.springdatamongodb.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task save(Task task) {
        task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTask(String taskId) {
        return taskRepository.findById(taskId).get();
    }

    public Task updateTask(Task taskRequest) {
        Task existingTask = taskRepository.findById(taskRequest.getTaskId()).get();// DB data
        existingTask.setTaskDescription(taskRequest.getTaskDescription());
        existingTask.setTaskDescription(taskRequest.getTaskDescription());
        existingTask.setTaskDescription(taskRequest.getTaskDescription());
        existingTask.setTaskDescription(taskRequest.getTaskDescription());
        return taskRepository.save(existingTask);
    }

    public String deleteTask(String taskId) {
        taskRepository.deleteById(taskId);
        return "Successfully deleted task " + taskId;
    }

}
