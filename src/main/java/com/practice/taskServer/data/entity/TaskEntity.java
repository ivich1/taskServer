package com.practice.taskServer.data.entity;

import com.practice.taskServer.data.dto.AcceptTaskDTO;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;//название задания
    private String worker;//исполнитель
    private int number;//число для проверки
    private boolean isPrime;//результат
    private String status;//статус задания

    @Temporal(TemporalType.TIMESTAMP)
    private Date getTaskTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date acceptTaskTime;


    public TaskEntity() {
    }

    public void addAnswer(AcceptTaskDTO task) {
        this.worker = task.worker;
        this.isPrime = task.isPrime;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isPrime() {
        return isPrime;
    }

    public void setPrime(boolean prime) {
        isPrime = prime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getGetTaskTime() {
        return getTaskTime;
    }

    public void setGetTaskTime(Date getTaskTime) {
        this.getTaskTime = getTaskTime;
    }

    public Date getAcceptTaskTime() {
        return acceptTaskTime;
    }

    public void setAcceptTaskTime(Date acceptTaskTime) {
        this.acceptTaskTime = acceptTaskTime;
    }
}