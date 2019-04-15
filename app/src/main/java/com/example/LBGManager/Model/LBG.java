package com.example.LBGManager.Model;


import com.example.LBGManager.Channel;

import java.lang.reflect.Member;
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

    public static List<Task> getUserTasks(String memeber_id) {
        List<Task> tasks = new ArrayList<>();
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
        Task task = null;
        for (Event event: events) {
            task = event.getTaskById(id);
            if (task!=null) {
                break;
            }
        }
        return task;
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



}
