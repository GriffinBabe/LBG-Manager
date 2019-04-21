package com.example.LBGManager.Network;

import com.example.LBGManager.Model.Event;
import com.example.LBGManager.Model.Member;
import com.example.LBGManager.Model.Model;
import com.example.LBGManager.Model.Task;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertNotEquals;

public class SessionTest {

    Session session;
    String username = "user_test";
    String password = "password_test";

    @Before
    @Test
    public void getInstance() {
        session = Session.getInstance(username, password);
        assertNotEquals(null, session);
        assertNotEquals(null, session.getToken());

    }

    @Test
    public void getModel() {
        Model model = session.gatherModel();
        for (Member member : model.getMembers()) {
            System.out.println(member.getMemeber_id()+" "+member.getName());
        }
        for (Task task : model.getTasks()) {
            System.out.println(task.getId()+" "+task.getResponsibles());
        }
        for (Event event : model.getEvents()) {
            System.out.println(event.getId()+" "+event.getName()+" "+event.getOrganisers_ids());
        }
        assertNotEquals(null, model);
    }

}