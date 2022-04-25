package com.practice.taskServer.data.repository;

import com.practice.taskServer.data.entity.TaskEntity;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<TaskEntity, Integer> {

    TaskEntity findByNumber(int number);
}
