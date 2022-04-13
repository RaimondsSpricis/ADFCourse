package rs.data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") private Integer id;
    @Column(name = "registered") private LocalDateTime registered = LocalDateTime.now();
    @Column(name = "first_name") private String firstName;
    @Column(name = "surname") private String surname;
    @Column(name = "email") private String email;
    @Column(name = "email_notification") private Boolean emailNotification = false;

    public Student() {}

    public Student(Integer id, LocalDateTime registered, String firstName, String surname, String email, Boolean emailNotification) {
        this.id = id;
        this.registered = registered;
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.emailNotification = emailNotification;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDateTime registered) {
        this.registered = registered;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEmailNotification() {
        return emailNotification;
    }

    public void setEmailNotification(Boolean emailNotification) {
        this.emailNotification = emailNotification;
    }
}
