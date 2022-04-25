package com.practice.taskServer;

import com.practice.worker.WorkerTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class TaskServerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskServerAppApplication.class, args);

		try{
			WorkerTest workerTest = new WorkerTest();
			workerTest.work();
			workerTest.work();
			workerTest.work();
		}catch (Exception e){
			System.out.print("fail");
		}

	}

}
