package com.example.LBGManager.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task {

    private String id;
    private String event_id;
    private String name, description;
    private Date deadline;
    private List<Member> responsibles;
    private boolean completed = false;

    public Task(String id, String event_id, String name, String description, Date deadline) {
        responsibles = new ArrayList<>();
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

    public void addResponsible(Member member) {
        this.responsibles.add(member);
    }

    public void removeResponsible(Member member){
        this.responsibles.remove(member);
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

    public List<Member> getResponsibles() {
        return responsibles;
    }

    public boolean containsResponsible(String member_id) {
        for (Member member : responsibles) {
            if (member.getMemeber_id().equals(member_id)) {
                return true;
            }
        }
        return  false;
    }
}
