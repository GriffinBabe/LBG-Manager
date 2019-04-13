package com.example.LBGManager.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event {

    private String id;
    private String name, description;
    private Date date_begin, date_end;
    private Member main_organiser;
    private List<Member> admins;
    private List<Member> organisers;
    private List<Task> tasks;

    public Event(String name, String description, Member main_organiser, Date date_begin, Date date_end) {
        this.date_begin = date_begin;
        this.date_end = date_end;
        this.organisers = new ArrayList<>();
        this.tasks = new ArrayList<>();
        this.admins = new ArrayList<>();
        this.name = name;
        this.description = description;

        this.main_organiser = main_organiser;
        admins.add(this.main_organiser);
    }

    public void addOrganiser(Member orga) {
        organisers.add(orga);
    }

    public void removeOrganiser(Member orga) {
        organisers.remove(orga);
        if (admins.contains(orga)) {
            admins.remove(orga);
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void addAdmin(Member member) {
        if (organisers.contains(member)) {
            admins.add(member);
        } else {
            System.out.println("Can't add: "+member.getName()+" as admin as it is not an Organiser");
        }
    }

    public List<Task> getUserTasks(String member_id) {
        List<Task> temp_tasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.containsResponsible(member_id)) {
                temp_tasks.add(task);
            }
        }
        return temp_tasks;
    }


}
