package com.example.application.backend.data.entity;

import java.sql.Timestamp;
import javax.persistence.*;

@Entity
@Table(name = "notifications")
public class CSPNotification extends BaseEntity {

  @Column
  private String topic;

  @Column
  private String content;


  @Column
  private Timestamp time;

  @Column
  private boolean seen = false;

  @ManyToOne(
      targetEntity = CSPNotification.class,
      cascade = CascadeType.PERSIST)
  @JoinColumn(
      name = "user_id",
      referencedColumnName = "id",
      nullable = false, unique = true)
  Employee employee;

  public CSPNotification() {
  }

  public String getContent() {
    return this.content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Employee getEmployee() {
    return this.employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public String getTopic() {
    return this.topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public Timestamp getTime() {
    return this.time;
  }

  public void setTime(Timestamp time) {
    this.time = time;
  }

  public boolean isSeen() {
    return this.seen;
  }

  public void setSeen(boolean seen) {
    this.seen = seen;
  }
}
