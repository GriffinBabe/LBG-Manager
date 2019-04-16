package com.example.LBGManager.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task {

    private String id;
    private String event_id;
    private String name, description;
    private Date deadline;
    private List<String> responsibles_ids;
    private boolean completed = false;

    public Task(String id, String event_id, String name, String description, Date deadline) {
        responsibles_ids = new ArrayList<>();
        this.event_id = event_id;
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
    }

    public String getId() {
        return id;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void addResponsible(String id) {
        this.responsibles_ids.add(id);
    }

    public void removeResponsible(String member){
        this.responsibles_ids.remove(member);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public List<String> getResponsibles() {
        return responsibles_ids;
    }

}
