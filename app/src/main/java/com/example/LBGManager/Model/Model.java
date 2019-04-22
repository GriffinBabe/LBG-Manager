package com.example.LBGManager.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Model implements Serializable {
    private List<Task> tasks = new ArrayList<>();
    private List<Event> events = new ArrayList<>();
    private List<Member> members = new ArrayList<>();

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
