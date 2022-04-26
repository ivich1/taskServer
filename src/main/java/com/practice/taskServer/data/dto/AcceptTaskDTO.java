package com.practice.taskServer.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigInteger;

//объект для представления отчета с результатом выполнения задачи
@JsonIgnoreProperties(ignoreUnknown = true)
public class AcceptTaskDTO {

    public String name;
    public String worker;
    public BigInteger number;
    public boolean isPrime;

    public AcceptTaskDTO() {;
    }
}
