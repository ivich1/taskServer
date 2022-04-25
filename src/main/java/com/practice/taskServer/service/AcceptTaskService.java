package com.practice.taskServer.service;

import com.practice.taskServer.data.dto.AcceptTaskDTO;
import com.practice.taskServer.data.entity.TaskEntity;
import com.practice.taskServer.data.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class AcceptTaskService {

    @Autowired
    TaskRepository taskRepository;

    public void saveTask(AcceptTaskDTO task){
        LocalDateTime localDateTime = LocalDateTime.now();//время сейчас
        Date d = Timestamp.valueOf(localDateTime);

        //ищем объект, дополняем, сохраняем
        TaskEntity toUpdate = taskRepository.findByNumber(task.number);
        toUpdate.addAnswer(task);
        toUpdate.setAcceptTaskTime(d);
        toUpdate.setStatus("done");
        taskRepository.save(toUpdate);
    }
}
