package com.code.empcrud.todoapp.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Todo {

    private long id;
    private String title;
    private String username;
    private String description;
    private LocalDate targetDate;
    private boolean status;

    public Todo(long id, String title, String username, String description, LocalDate targetDate, boolean status) {
        this.id = id;
        this.title = title;
        this.username = username;
        this.description = description;
        this.targetDate = targetDate;
        this.status = status;
    }

    public Todo(String title, String username, String description, LocalDate targetDate, boolean status) {
        this.title = title;
        this.username = username;
        this.description = description;
        this.targetDate = targetDate;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        Todo other = (Todo) obj;
        if(id != other.getId()) return false;
        return true;
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + (int)(id ^ (id >>> 32));
        return result;
    }
}
