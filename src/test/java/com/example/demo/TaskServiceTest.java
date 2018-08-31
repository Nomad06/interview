package com.example.demo;

import com.example.demo.dto.TaskDto;
import com.example.demo.entity.Task;
import com.example.demo.enums.Status;
import com.example.demo.service.TaskService;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskServiceTest {

  @Autowired
  private TaskService taskService;

  @Test
  public void newTaskTest(){
    Task newTask = taskService.newTask();
    Assert.assertNotNull(newTask);
  }

  @Test
  public void runTask() throws Exception {
    Task task = taskService.newTask();
    taskService.runTask(task);
    TimeUnit.SECONDS.sleep(2);
    TaskDto dto = taskService.checkStatus(task.getId().toString());
    Assert.assertEquals(dto.getStatus(), Status.R.getName());
    TimeUnit.SECONDS.sleep(125);
    dto = taskService.checkStatus(task.getId().toString());
    Assert.assertEquals(dto.getStatus(), Status.F.getName());
  }

  @Test
  public void checkStatus() throws Exception {
    UUID uuid = UUID.randomUUID();
    TaskDto dto = taskService.checkStatus(uuid.toString());
    Assert.assertNull(dto);

    Task task = taskService.newTask();
    dto = taskService.checkStatus(task.getId().toString());
    Assert.assertNotNull(dto);
  }

}
