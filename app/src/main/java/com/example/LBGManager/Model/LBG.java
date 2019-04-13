package com.example.LBGManager.Model;

import android.media.MediaMetadata;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

public class LBG {

    private static List<Event> events = new ArrayList<>();
    private static List<Member> members = new ArrayList<>();
    private static AppMember app_user = AppMember.getInstance();

    public static void updateModel() {
        // TODO: All network stuff
    }

    public static List<Task> getUserTasks(String memeber_id) {
        List<Task> tasks = new ArrayList<>();
        for (Event e : events) {
            tasks.addAll(e.getUserTasks(memeber_id));
        }
        return tasks;
    }


}
