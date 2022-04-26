package com.practice.taskServer.data.dto;

import com.practice.taskServer.data.entity.TaskEntity;

import java.math.BigInteger;

//объект для пердставления информации в таблицу
public class TableViewDTO {

    public String id;
    public String name;
    public String worker;//исполнитель
    public String number;//число для проверки
    public String isPrime;//результат
    public String status;//статус задания

    public TableViewDTO() {
    }

    public TableViewDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public TableViewDTO(TaskEntity source) {
        this.id = Integer.toString(source.getId());
        this.name = source.getName();
        this.worker = source.getWorker();
        this.number = source.getNumber().toString();
        this.isPrime = Boolean.toString(source.isPrime());
        this.status = source.getStatus();
    }
}
