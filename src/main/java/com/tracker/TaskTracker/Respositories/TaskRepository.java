package com.tracker.TaskTracker.Respositories;

import com.tracker.TaskTracker.Entities.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Tasks, Long> {
}
