package com.example.demo;

import com.example.demo.entity.Task;
import com.example.demo.enums.Status;
import com.example.demo.repository.TaskRepository;
import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TaskRepositoryTest {

  @Autowired
  private TaskRepository taskRepository;

  @Test
  public void taskSaveTest(){
    Task savedTask = saveSomeTask();
    Assert.assertNotNull(savedTask);
  }

  @Test
  public void findTaskTest(){
    Task savedTask = saveSomeTask();

    Optional<Task> foundTask = taskRepository.findById(savedTask.getId());
    Assert.assertTrue(foundTask.isPresent());
  }

  private Task saveSomeTask(){
    UUID id = UUID.randomUUID();
    Task task = new Task(Status.C, Calendar.getInstance().getTime(),
        Calendar.getInstance().getTime());

    return taskRepository.save(task);
  }

}
