package com.tracker.TaskTracker.Service;

import com.tracker.TaskTracker.Entities.Tasks;
import com.tracker.TaskTracker.Respositories.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository repository;


    @Autowired
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Tasks> getAllEntities(){

        return repository.findAll(Sort.by(Sort.Direction.ASC,"id"));
    }

    public Tasks getTaskById(Long id) {
        return repository.findById(id).orElse(null);
    }
    public boolean deleteById(Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }
    public Tasks createTask(Tasks task){
        return repository.save(task);
    }
    public Tasks updateTask(Long id, Tasks updatedTask) {
        if (repository.existsById(id)) {
            updatedTask.setId(id);
            return repository.save(updatedTask);
        }
        return null; // Task not found
    }
}
