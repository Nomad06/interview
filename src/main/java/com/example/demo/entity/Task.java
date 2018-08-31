package com.example.demo.entity;

import com.example.demo.enums.Status;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Task {
  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  private UUID id;
  @Column
  private Status status;
  @Column(nullable = false)
  private Date insstmp;
  @Column(nullable = false)
  private Date updstmp;

  public Task() {
  }

  public Task(Status status, Date insstmp, Date updstmp) {
    this.status = status;
    this.insstmp = insstmp;
    this.updstmp = updstmp;
  }

  public Task(UUID id, Status status, Date insstmp, Date updstmp) {
    this.id = id;
    this.status = status;
    this.insstmp = insstmp;
    this.updstmp = updstmp;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Date getInsstmp() {
    return insstmp;
  }

  public void setInsstmp(Date insstmp) {
    this.insstmp = insstmp;
  }

  public Date getUpdstmp() {
    return updstmp;
  }

  public void setUpdstmp(Date updstmp) {
    this.updstmp = updstmp;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Task copy(){
    Task task = new Task();
    task.setInsstmp(this.insstmp);
    task.setStatus(this.status);
    task.setUpdstmp(this.updstmp);

    return task;
  }

  @Override
  public String toString() {
    return "Task{" +
        "gid=" + id +
        ", status='" + status.getName() + '\'' +
        ", insstmp=" + insstmp +
        ", updstmp=" + updstmp +
        '}';
  }
}
