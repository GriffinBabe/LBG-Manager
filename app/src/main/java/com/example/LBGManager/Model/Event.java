package com.example.LBGManager.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event {

    private String id;
    private String name, description;
    private Date date_begin, date_end;
    private String main_organiser_id;
    private List<String> admins_ids;
    private List<String> organisers_ids;
    private List<String> tasks_ids;


    public Event(String id, String name, String description, String main_organiser_id, Date date_begin, Date date_end) {
        this.id = id;
        this.date_begin = date_begin;
        this.date_end = date_end;
        this.organisers_ids = new ArrayList<>();
        this.tasks_ids = new ArrayList<>();
        this.admins_ids = new ArrayList<>();
        this.name = name;
        this.description = description;

        this.main_organiser_id = main_organiser_id;
        admins_ids.add(main_organiser_id);
        organisers_ids.add(main_organiser_id);
    }

    public String getId() {
        return id;
    }
    public void addOrganiser(String orga) {
        organisers_ids.add(orga);
    }

    public void removeOrganiser(String orga) {
        organisers_ids.remove(orga);
        if (admins_ids.contains(orga)) {
            admins_ids.remove(orga);
        }
    }

    public void addTask(String task) {
        tasks_ids.add(task);
        LBG.notifyObservers();
    }

    public void addAdmin(String member) {
        if (organisers_ids.contains(member)) {
            admins_ids.add(member);
        } else {
            System.out.println("Can't add: "+member+" as admin as it is not an Organiser");
        }
    }

}
