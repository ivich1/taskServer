package com.practice.worker;

import com.google.gson.Gson;
import com.practice.taskServer.data.dto.AcceptTaskDTO;
import com.practice.taskServer.data.dto.GetTaskDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WorkerTest {
    //создание коннекта на подключение
    private final URL getURL = new URL("http://localhost:8080/get");
    private final URL postURL = new URL("http://localhost:8080/post");
    private HttpURLConnection getConnection;
    private HttpURLConnection postConnection;

    private final String workerName;

    public WorkerTest() throws MalformedURLException {
        workerName = "workerTest";
    }
    public WorkerTest(String workerName) throws MalformedURLException {
        this.workerName = workerName;
    }

    public void work(){
        //соединение
        setConnectionGet();
        //работа, преобразования к отправке
        AcceptTaskDTO res = calculate();
        Gson g = new Gson();
        String s = g.toJson(res);
        //отправка
        postAnswer(s);
    }

    public void setConnectionGet(){
        try{
            getConnection = (HttpURLConnection) getURL.openConnection();
            getConnection.setRequestMethod("GET");
            getConnection.setRequestProperty("Content-Type", "application/json");
        }catch (Exception e){
            //тут ошибка
        }
    }

    public String postAnswer(String toPost){
        //превращение объекта в json
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(toPost, headers);

        RestTemplate restTemplate = new RestTemplate();

        return  restTemplate.postForObject("http://localhost:8080/post", request, String.class);
    }

    //не работает
    public void postAnswer(String jsonAnswerString, int a){
        try{
            postConnection = (HttpURLConnection) postURL.openConnection();
            postConnection.setRequestMethod("POST");
            postConnection.setRequestProperty("Content-Type", "application/json");
            postConnection.setRequestProperty("Accept", "application/json");
            postConnection.setDoOutput(true);
            try(OutputStream os = postConnection.getOutputStream()) {
                byte[] input = jsonAnswerString.getBytes("utf-8");
                os.write(input, 0, input.length);
                os.flush();
            }
        }catch (Exception e){
            //тут ошибка
        }
    }

    public AcceptTaskDTO calculate(){
        var info = getInfo();
        GetTaskDTO task = toTaskDTO(info);
        boolean isPrime = isPrime(task.number);
        AcceptTaskDTO answer =  createAnswer(task.number, isPrime);
        return answer;
    }

    //преобразует stream в строку(json строку)
    private String getInfo(){
        try (final BufferedReader in = new BufferedReader(new InputStreamReader(getConnection.getInputStream()))) {
            String inputLine;
            final StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            return content.toString();
        } catch (final Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    //преобразует json строку в класс GetTaskDTO
    private GetTaskDTO toTaskDTO(String jsonString){
        Gson g = new Gson();
        GetTaskDTO task = g.fromJson(jsonString, GetTaskDTO.class);
        return task;
    }

    public boolean isPrime(int number){
        int n = number/2;
        for(int i = 2; i< n; i++){
            if (number % i == 0){
                return false;
            }
        }
        return true;
    }

    private AcceptTaskDTO createAnswer(int number, boolean isPrime){
        AcceptTaskDTO answer = new AcceptTaskDTO();
        answer.name = "test";
        answer.worker = this.workerName;
        answer.number = number;
        answer.isPrime = isPrime;
        return answer;
    }
}
