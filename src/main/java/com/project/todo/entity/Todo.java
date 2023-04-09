package com.project.todo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "Todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "Title")
    private String title;
    @Column(name = "Message")
    private String message;
    @Column(name = "InsertionTime")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Kolkata")
    private Timestamp insertionTime;
    @Column(name = "userid")
    private int userId;

    public Todo(String title, String message, Timestamp insertionTime) {
        this.title = title;
        this.message = message;
        this.insertionTime = insertionTime;
    }

    public Todo() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getInsertionTime() {
        return insertionTime;
    }

    public void setInsertionTime(Timestamp insertionTime) {
        this.insertionTime = insertionTime;
    }
}
