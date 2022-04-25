package com.practice.taskServer.controller;

import com.practice.taskServer.data.dto.AcceptTaskDTO;
import com.practice.taskServer.data.dto.GetTaskDTO;
import com.practice.taskServer.service.AcceptTaskService;
import com.practice.taskServer.service.GetTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @Autowired
    GetTaskService getTaskService;
    @Autowired
    AcceptTaskService acceptTaskService;

    @GetMapping("/get")
    public GetTaskDTO getTask(){
        return getTaskService.getTask();
    }

    @GetMapping("/getsimple")
    public GetTaskDTO getTaskSimple(){
        return getTaskService.getTaskSimple();
    }

    //https://www.baeldung.com/spring-resttemplate-post-json
    //тут ошибка
    @PostMapping("/post")
    public ResponseEntity acceptTsk(
            @RequestBody AcceptTaskDTO acceptTaskDTO
            ){
        acceptTaskService.saveTask(acceptTaskDTO);
        return ResponseEntity.ok("ответ принят");
    }

}
