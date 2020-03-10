package com.example.application.backend.data.models;

import java.sql.Timestamp;

public class CSPNotificationDTO {

  private Long id;
  private String content;
  private String topic;
  private Timestamp time;
  private boolean seen;

  public CSPNotificationDTO() {
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getContent() {
    return this.content;
  }

  public void setContent(String content) {
    this.content = content;
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
