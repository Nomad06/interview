package com.example.demo.controller;

import com.example.demo.dto.TaskDto;
import com.example.demo.entity.Task;
import com.example.demo.service.TaskService;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/task")
public class TaskController {

  private final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

  @Autowired
  private TaskService taskServiceImpl;

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<?> launchTask() throws InterruptedException {
    LOGGER.info("Instantiate task");
    Task task = taskServiceImpl.newTask();
    LOGGER.info("Launch task");
    taskServiceImpl.runTask(task);

    return new ResponseEntity<>(task.getId(), HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<?> checkStatus(@PathVariable String id) {
    TaskDto task;
    try {
      LOGGER.info("Checking uuid");
      UUID.fromString(id);
      task = taskServiceImpl.checkStatus(id);
    }
    catch (IllegalArgumentException ex){
      LOGGER.error("Incorrect UUID", ex);
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    catch (Exception e) {
      LOGGER.error("Error during task search", e);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(task, task == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
  }
}
