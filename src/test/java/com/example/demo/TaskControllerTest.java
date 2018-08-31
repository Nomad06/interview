package com.example.demo;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.controller.TaskController;
import com.example.demo.entity.Task;
import com.example.demo.service.TaskService;
import java.util.UUID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
public class TaskControllerTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private TaskService taskService;

  @Test
  public void launchTaskTest() throws Exception {
    mvc.perform(post("/task")
    .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void checkStatusWithWrongIdTest() throws Exception {
    UUID uuid = UUID.randomUUID();
    mvc.perform(get("/task/" + uuid)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  public void checkStatusTest() throws Exception {
    Task task = taskService.newTask();
    mvc.perform(get("/task/" + task.getId())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }
}
