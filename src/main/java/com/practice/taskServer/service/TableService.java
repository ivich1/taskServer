package com.practice.taskServer.service;

import com.practice.taskServer.data.dto.TableViewDTO;
import com.practice.taskServer.data.entity.TaskEntity;
import com.practice.taskServer.data.repository.TaskRepository;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class TableService {

    @Autowired
    private TaskRepository taskRepository;

    public Iterable<TableViewDTO> getTaskTable(){
        return toTableViewDTO(taskRepository.findAll());
    }

    private Iterable<TableViewDTO> toTableViewDTO(Iterable<TaskEntity> source){
        ArrayList<TableViewDTO> res = new ArrayList<>();
        for(TaskEntity unit: source){
            var tmp = new TableViewDTO(unit);
            res.add(tmp);
            //--------------------date--------------------
            System.out.println("get>>" + unit.getGetTaskTime());
            System.out.println("time==" + executionTime(unit.getGetTaskTime(),unit.getAcceptTaskTime()));
            System.out.println("put<<" + unit.getAcceptTaskTime());
        }
        return res;
    }

    private long executionTime(Date getDate, Date postDate){
        //return DateUtils.addDays(getDate,10);
        return postDate.getTime() - getDate.getTime();
    }


}
