package com.practice.taskServer.service;

import com.practice.taskServer.data.dto.GetTaskDTO;
import com.practice.taskServer.data.entity.TaskEntity;
import com.practice.taskServer.data.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

@Service
public class GetTaskService {

    @Autowired
    TaskRepository taskRepository;

    public GetTaskDTO getTask(){
        return createTask();
    }

    //дает гарантированно простое число (113)
    public GetTaskDTO getTaskSimple(){
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setNumber(113);
        taskEntity.setName(getTaskName(taskEntity.getNumber()));//чтоб не было колизии
        taskEntity.setStatus("inWork");
        LocalDateTime localDateTime = LocalDateTime.now();//время сейчас
        Date d = Timestamp.valueOf(localDateTime);
        taskEntity.setGetTaskTime(d);

        taskRepository.save(taskEntity);
        return new GetTaskDTO(taskEntity);
    }

    //создает запись задания в таблице
    private GetTaskDTO createTask(){
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setNumber(getNextIntToCheck());
        taskEntity.setName(getTaskName(taskEntity.getNumber()));//чтоб не было колизии
        taskEntity.setStatus("inWork");
        LocalDateTime localDateTime = LocalDateTime.now();//время сейчас
        Date d = Timestamp.valueOf(localDateTime);
        taskEntity.setGetTaskTime(d);

        taskRepository.save(taskEntity);
        return new GetTaskDTO(taskEntity);
    }

    //выдает число на проверку, работает приметивно можно развить
    //использует число
    //добавляет случайную величину к нему и отправляет результат
    private int toCheck = 1;
    private int getNextIntToCheck(){
        Random random = new Random();
        toCheck += random.nextInt(1000 + 1);//чтоб 0 не падал
        return toCheck;
    }

    //устанвливает названиек задания по номеру
    private String getTaskName(int i){
        return "task" + i;
    }

}
