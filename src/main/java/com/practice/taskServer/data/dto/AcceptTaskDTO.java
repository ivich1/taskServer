package com.practice.taskServer.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//объект для представления отчета с результатом выполнения задачи
@JsonIgnoreProperties(ignoreUnknown = true)
public class AcceptTaskDTO {

    public String name;
    public String worker;
    public int number;
    public boolean isPrime;

    public AcceptTaskDTO() {;
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
}
