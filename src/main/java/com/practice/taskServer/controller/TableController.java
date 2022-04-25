package com.practice.taskServer.controller;

import com.practice.taskServer.data.dto.TableViewDTO;
import com.practice.taskServer.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class TableController {

    @Autowired
    TableService tableService;

    @GetMapping
    public String greeting(){
        return "greeting";
    }

    @GetMapping("/table")
    public String table(Map<String, Object> model){
        Iterable<TableViewDTO> tasks = tableService.getTaskTable();
        model.put("tasks", tasks);
        return "table";
    }
}
