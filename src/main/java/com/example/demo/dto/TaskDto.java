package com.example.demo.dto;

import com.example.demo.entity.Task;
import java.util.Date;

//TODO need some converter for converting from entity to dto and back
public class TaskDto {

  private String status;
  private Date lastUpdate;

  TaskDto(){}

  public TaskDto(String status, Date lastUpdate) {
    this.status = status;
    this.lastUpdate = lastUpdate;
  }

  public TaskDto(Task task){
    this.status = task.getStatus().getName();
    this.lastUpdate = task.getUpdstmp();
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Date getLastUpdate() {
    return lastUpdate;
  }

  public void setLastUpdate(Date lastUpdate) {
    this.lastUpdate = lastUpdate;
  }
}
