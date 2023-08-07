package com.tracker.TaskTracker.APIControllers;

import com.tracker.TaskTracker.Entities.Tasks;
import com.tracker.TaskTracker.Service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class TaskController {
    private final TaskService service;
    private final Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping("/tasks")
    public ResponseEntity<?> getAllTasks(){
        logger.info("Getting all entities.");
        List<Tasks> list=service.getAllEntities();
        if(list.isEmpty()){
            logger.warn("Table Empty.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("No Tasks Found in the Database, Try locahost:8080/tasks using POST. ");
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<?> getById(@PathVariable(required = false) String id) {
        logger.info("Getting data by id: "+ id);
        try {
            Long idOfEntity = Long.parseLong(id);
            if (service.getTaskById(idOfEntity) == null) {
                logger.warn("No data found for id: "+ id);
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                        .body("Id: " + id + " does not exist.");
            }
            return ResponseEntity.ok(service.getTaskById(idOfEntity));
        } catch (NumberFormatException nfe) {
            logger.warn("Number Format Exception encountered.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Please insert numbers for id, any other data type is not acceptable.");
        }
    }
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id){
        logger.info("Deleting entry with id"+ id);
            try {
                Long idOfEntity = Long.parseLong(id);
                if (service.getTaskById(idOfEntity) == null) {
                    logger.warn("Entry not found with id: "+id);
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                            .body("Id: " + id + " does not exist.");
                }
                return ResponseEntity.ok(service.deleteById(idOfEntity));
            } catch (NumberFormatException nfe) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Please insert numbers for id, any other data type is not acceptable.");
            }
    }
    @PostMapping("/tasks")
    public ResponseEntity<?> createNewTask(@RequestBody Tasks task){
        logger.info("Inserting a new entry into the table: "+ task.toString());
        try {
            Tasks createdTask = service.createTask(task);

            if (task != null) {

                return ResponseEntity.status(HttpStatus.CREATED)
                        .body("Entry Created: " + createdTask.toString());
            } else {
                logger.info("No data entered.");
                return ResponseEntity.badRequest().body("Failed to create task.");
            }
        }catch (NumberFormatException nfe) {
            logger.info("Number format Exception Encountered.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Please insert numbers for id, any other data type is not acceptable.");
        }
    }

    @PutMapping("tasks/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody Tasks updatedTask) {
        Tasks updated = service.updateTask(id, updatedTask);
        logger.info("Updating existing entry with the id: "+updatedTask.getId());
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            logger.info("No entry with the id: "+ updatedTask.getId());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found.");
        }
    }
}
