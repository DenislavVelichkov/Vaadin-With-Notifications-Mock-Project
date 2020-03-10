package com.example.application.backend.data.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "employees")
public class Employee extends BaseEntity {

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "title")
    private String title;

    @OneToOne
    @JoinColumn(
        name = "user_id",
        referencedColumnName = "id",
        nullable = false, unique = true)
    private User userCredentials;

    public Employee() {
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUserCredentials() {
        return this.userCredentials;
    }

    public void setUserCredentials(User userCredentials) {
        this.userCredentials = userCredentials;
    }

    @Override
    public int hashCode() {

        if (super.getId() == null) {
            return super.hashCode();
        } else {
            return super.getId().intValue();
        }
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null || super.getId() == null) {
            return false;
        }

        if (!(obj instanceof Employee)) {
            return false;
        }

        if (super.getId().equals(((Employee) obj).getId())) {
            return true;
        }

        return false;
    }
}
