package com.example.demo.service;

import com.example.demo.dto.TaskDto;
import com.example.demo.entity.Task;
import com.example.demo.enums.Status;
import com.example.demo.repository.TaskRepository;
import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class TaskServiceImpl implements TaskService{

  private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
  private final long RUNNING_DELAY = 1;
  private final long FINISH_DELAY = TimeUnit.MINUTES.toSeconds(2);

  @Autowired
  private TaskRepository taskRepository;

  public Task newTask() {
    UUID id = UUID.randomUUID();
    Task task = new Task(Status.C, Calendar.getInstance().getTime(),
        Calendar.getInstance().getTime());
    LOGGER.info("Try to create task : \n" + task);
    return taskRepository.save(task);
  }

  @Override
  public TaskDto checkStatus(String id) throws Exception {
    LOGGER.info("Check status of task with id : " + id);
    Optional<Task> task = taskRepository.findById(UUID.fromString(id));

    return task.map(TaskDto::new).orElse(null);
  }

  @Override
  @Async
  public void runTask(Task task) {
    try {
      //RUNNING_DELAY used for short pause which allow to switch and return task id by controller
      //looks like kostil' but "eto norma!"
      changeStatusWithDelay(RUNNING_DELAY, Status.R, task);
      changeStatusWithDelay(FINISH_DELAY, Status.F, task);
    } catch (InterruptedException e) {
      LOGGER.error("Error during task execution", e);
    }
  }

  private void changeStatusWithDelay(long delay, Status status, Task task) throws InterruptedException {
    TimeUnit.SECONDS.sleep(delay);
    LOGGER.info("Change task status from " + task.getStatus().getName() + " to " + status.getName());
    task.setStatus(status);
    taskRepository.save(task);
  }



}
