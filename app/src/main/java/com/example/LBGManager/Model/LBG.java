package com.example.LBGManager.Model;


import com.example.LBGManager.Channel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LBG {

    private static List<Event> events = new ArrayList<>();
    private static List<Member> members = new ArrayList<>();
    private static List<Task> tasks = new ArrayList<>();
    private static AppMember app_user = AppMember.getInstance();

    private static List<Channel> observers = new ArrayList<>();

    public static void updateModel() {
        // TODO: All network stuff
        notifyObservers();
    }

    public static List<Task> getUserTasks(String member_id) {
        List<Task> temp_tasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getResponsibles().contains(member_id)) {
                temp_tasks.add(task);
            }
        }
        tasks.add(new Task("AAA1","1","Courses","Aller acheter des courses au Colruyt",new Date(2020,10,31)));
        tasks.add(new Task("AAA2","1","Netoyage","Netoyer le local",new Date(2020,10,30)));
        tasks.add(new Task("AAA3","1","Netoyage","Netoyer le local",new Date(2020,10,30)));
        tasks.add(new Task("AAA4","1","Netoyage","Netoyer le local",new Date(2020,10,30)));
        tasks.add(new Task("AAA5","1","Netoyage","Netoyer le local",new Date(2020,10,30)));
        tasks.add(new Task("AAA6","1","Netoyage","Netoyer le local",new Date(2020,10,30)));
        tasks.add(new Task("AAA7","1","Netoyage","Netoyer le local",new Date(2020,10,30)));
        tasks.add(new Task("AAA8","1","Netoyage","Netoyer le local",new Date(2020,10,30)));
        tasks.add(new Task("AAA9","1","Netoyage","Netoyer le local",new Date(2020,10,30)));
        tasks.add(new Task("AAA10","1","Netoyage","Netoyer le local",new Date(2020,10,30)));
        tasks.add(new Task("AAA11","1","Netoyage","Netoyer le local",new Date(2020,10,30)));
        return tasks;
        /*
        List<Task> tasks = new ArrayList<>();
        for (Event e : events) {
            tasks.addAll(e.getUserTasks(memeber_id));
        }
        return tasks;*/
    }

    public static Event getEventById(String id)  {
        for (Event event : events) {
            if (event.getId().equals(id)) {
                return event;
            }
        }
        return null;
    }

    public static Task getTaskById(String id) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }

    public static Member getMemberById(String id) {
        for (Member member : members) {
            if (member.getMemeber_id().equals(id)) {
                return member;
            }
        }
        return null;
    }

    /**
     * Notifies every Channel objects that the state has changed
     */
    public static void notifyObservers() {
        for (Channel channel : observers) {
            channel.stateChanged();
        }
    }

    public static void addObserver(Channel channel) {
        observers.add(channel);
    }

    /**
     * This function will return every memeber id from the LBG, but the first elements are the organisers of the event
     * @param event_id The event identification number
     * @return A list containing all LBG memebers ids but the first elements are the organisers
     */
    public static List<String> getAllMembersFromEventId(String event_id) {
        Event event = getEventById(event_id);
        List<String> members_ids = new ArrayList<>();
        // First adds every member id from the event organisers
        members_ids.addAll(event.getOrganisers_ids());

        // Then adds all the member id from the LBG
        for (Member member : members) {
            String id = member.getMemeber_id();
            if (!members_ids.contains(id)) {
                members_ids.add(id);
            }
        }

        return members_ids;
    }


}
