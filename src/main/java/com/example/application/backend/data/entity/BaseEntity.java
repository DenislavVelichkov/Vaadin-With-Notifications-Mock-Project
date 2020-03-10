package com.example.application.backend.data.entity;

import java.io.Serializable;
import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id",
      updatable = false,
      unique = true,
      nullable = false)
  private Long id;

  public BaseEntity() {
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
