package com.example.LBGManager.Network;


/**
 * This class creates and stores the account token necessary for requestes
 */
public class Session {

    private static String token;
    private static Session INSTANCE;

    private Session(String username, String password) {
        if (token==null) {
            createToken(username, password);
        }
    }

    public static Session getInstance(String username, String password) {
        if (INSTANCE==null) {
            INSTANCE = new Session(username, password);
        }
        return INSTANCE;
    }

    private void createToken(String username, String password) {

    }

    public String getToken() {
        // TODO: Sends a Json to ask a token
        return token;
    }
}
