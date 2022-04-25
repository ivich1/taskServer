package com.practice.taskServer.data.dto;

import com.practice.taskServer.data.entity.TaskEntity;

//объект для представления информации о задаче
public class GetTaskDTO {

    public String name;
    public int number;

    public GetTaskDTO() {
    }

    public GetTaskDTO(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public GetTaskDTO(TaskEntity taskEntity) {
        this.name = taskEntity.getName();
        this.number = taskEntity.getNumber();
    }
}
