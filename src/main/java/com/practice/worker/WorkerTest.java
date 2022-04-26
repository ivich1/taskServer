package com.practice.worker;

import com.google.gson.Gson;
import com.practice.taskServer.data.dto.AcceptTaskDTO;
import com.practice.taskServer.data.dto.GetTaskDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.net.HttpURLConnection;

public class WorkerTest {
    //url, по идее должно быть в пропах, но пусть будут тут
    private final String getURL = "http://localhost:8080/get";
    private final String postURL = "http://localhost:8080/post";

    private final String workerName;
    private int workCount;//кол-во оставшихся возможностей поработать

    public WorkerTest() {
        workerName = "workerTest";
        this.workCount = -1;
    }

    public WorkerTest(String workerName){
        this.workerName = workerName;
        this.workCount = -1;
    }

    public WorkerTest(String workerName, int workCount){
        this.workerName = workerName;
        this.workCount = workCount;
    }

    public boolean work(){
        if(workCount == 0){
            return false;
        }
        workCount --;
        //получение
        GetTaskDTO task = getTask();
        //вычисление
        AcceptTaskDTO ans = calculate(task);
        //отправка
        postAnswer(ans);

        return true;
    }

    //получение задания
    private GetTaskDTO getTask(){
        RestTemplate restTemplate = new RestTemplate();
        GetTaskDTO task = restTemplate.getForObject(getURL, GetTaskDTO.class);
        return task;
    }

    //отправка объекта
    private String postAnswer(AcceptTaskDTO toPost){
        //превращение объекта в json
        Gson g = new Gson();
        String jsonToPost = g.toJson(toPost);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(jsonToPost, headers);

        RestTemplate restTemplate = new RestTemplate();
        return  restTemplate.postForObject(postURL, request, String.class);
    }

    private AcceptTaskDTO calculate(GetTaskDTO task){
        boolean isPrime = isPrime(task.number);
        AcceptTaskDTO answer =  createAnswer(task.number, isPrime);
        return answer;
    }

    //считает простое ли число
    private boolean isPrime(BigInteger number){
        BigInteger n = number.divide(BigInteger.TWO);
        for(BigInteger i = BigInteger.TWO; i.compareTo(n) == -1; i.add(BigInteger.ONE)){
            if (number.mod(i) == BigInteger.ZERO){
                return false;
            }
        }
        return true;
    }

    //собирает ответ
    private AcceptTaskDTO createAnswer(BigInteger number, boolean isPrime){
        AcceptTaskDTO answer = new AcceptTaskDTO();
        answer.name = "task" + number;
        answer.worker = this.workerName;
        answer.number = number;
        answer.isPrime = isPrime;
        return answer;
    }

    public int getWorkCount() {
        return workCount;
    }

    public String getWorkerName() {
        return workerName;
    }
}
