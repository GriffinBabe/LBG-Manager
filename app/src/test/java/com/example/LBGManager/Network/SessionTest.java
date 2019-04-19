package com.example.LBGManager.Network;

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

}