package com.example.LBGManager.Model;

import android.content.Context;

import java.io.*;

public class Serializer {

    private static String MODEL_FILE = "model.ser";
    private static String TOKEN_FILE = "token.ser";

    public static void serialize(Object o) throws  IOException {
        FileOutputStream fos;
        ObjectOutputStream oos;
        if (o instanceof Model) {
            fos = new FileOutputStream(MODEL_FILE);
        } else if (o instanceof AppMember) {
            fos = new FileOutputStream(TOKEN_FILE);
        } else {
            throw new IOException("Cannot serialize: " + o.getClass().getName());
        }
        oos = new ObjectOutputStream(fos);
        oos.writeObject(o);
        fos.close();
    }
    public static Model deserializeModel() throws  IOException, ClassNotFoundException{
        Model model;
        FileInputStream fis = new FileInputStream(MODEL_FILE);
        ObjectInputStream ois = new ObjectInputStream(fis);
        model = (Model) ois.readObject();
        fis.close();
        ois.close();
        return model;
    }

    public static AppMember deserializeAppMember() throws  IOException, ClassNotFoundException {
        AppMember member;
        FileInputStream fis = new FileInputStream(MODEL_FILE);
        ObjectInputStream ois = new ObjectInputStream(fis);
        member = (AppMember) ois.readObject();
        fis.close();
        ois.close();
        return member;
    }
}
