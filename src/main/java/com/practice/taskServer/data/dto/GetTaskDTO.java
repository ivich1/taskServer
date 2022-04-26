package com.practice.taskServer.data.dto;

import com.practice.taskServer.data.entity.TaskEntity;

import java.math.BigInteger;

//объект для представления информации о задаче
public class GetTaskDTO {

    public String name;
    public BigInteger number;

    public GetTaskDTO() {
    }

    public GetTaskDTO(String name, BigInteger number) {
        this.name = name;
        this.number = number;
    }

    public GetTaskDTO(TaskEntity taskEntity) {
        this.name = taskEntity.getName();
        this.number = taskEntity.getNumber();
    }
}
