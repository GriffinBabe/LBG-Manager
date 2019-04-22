package com.example.LBGManager.Model;

import android.content.Context;

import java.io.*;

public class Serializer {

    private static String MODEL_FILE = "model.ser";
    private static String APPMEMBER_FILE = "token.ser";

    public static void serialize(Object o, Context context) throws  IOException {
        FileOutputStream fos;
        ObjectOutputStream oos;
        if (o instanceof Model) {
            fos = context.openFileOutput(MODEL_FILE, Context.MODE_PRIVATE);
        } else if (o instanceof AppMember) {
            fos = new FileOutputStream(APPMEMBER_FILE);
        } else {
            throw new IOException("Cannot serialize: " + o.getClass().getName());
        }
        oos = new ObjectOutputStream(fos);
        oos.writeObject(o);
        fos.close();
    }
    public static Model deserializeModel(Context context) throws  IOException, ClassNotFoundException{
        Model model;
        FileInputStream fis = context.openFileInput(MODEL_FILE);
        ObjectInputStream ois = new ObjectInputStream(fis);
        model = (Model) ois.readObject();
        fis.close();
        ois.close();
        return model;
    }

    public static AppMember deserializeAppMember(Context context) throws  IOException, ClassNotFoundException {
        AppMember member;
        FileInputStream fis = context.openFileInput(APPMEMBER_FILE);
        ObjectInputStream ois = new ObjectInputStream(fis);
        member = (AppMember) ois.readObject();
        fis.close();
        ois.close();
        return member;
    }
}
