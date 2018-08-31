package com.example.demo.service;


import com.example.demo.dto.TaskDto;
import com.example.demo.entity.Task;

public interface TaskService {

  public void runTask(Task task);

  public Task newTask();

  public TaskDto checkStatus(String id) throws Exception;

}
