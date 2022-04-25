package com.practice.taskServer.service;

import com.practice.taskServer.data.dto.TableViewDTO;
import com.practice.taskServer.data.entity.TaskEntity;
import com.practice.taskServer.data.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import javax.persistence.Table;
import java.util.ArrayList;
import java.util.LinkedList;

@Service
public class TableService {

    @Autowired
    private TaskRepository taskRepository;

    public Iterable<TableViewDTO> getTaskTable(){
        //taskRepository.save(new TaskEntity("first"));
        //taskRepository.save(new TaskEntity("second"));
        return toTableViewDTO(taskRepository.findAll());
    }

    private Iterable<TableViewDTO> toTableViewDTO(Iterable<TaskEntity> source){
        ArrayList<TableViewDTO> res = new ArrayList<>();
        for(TaskEntity unit: source){
            var tmp = new TableViewDTO(unit);
            res.add(tmp);
        }
        return res;
    }


}
