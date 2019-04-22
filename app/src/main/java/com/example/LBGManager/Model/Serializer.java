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
            File file = new File(context.getFilesDir()+"/"+MODEL_FILE);
            fos = new FileOutputStream(file);
        } else if (o instanceof AppMember) {
            File file = new File(context.getFilesDir()+"/"+APPMEMBER_FILE);
            fos = new FileOutputStream(file);
        } else {
            throw new IOException("Cannot serialize: " + o.getClass().getName());
        }
        oos = new ObjectOutputStream(fos);
        oos.writeObject(o);
        fos.close();
    }
    public static Model deserializeModel(Context context) throws  IOException, ClassNotFoundException{
        Model model;
        FileInputStream fis = new FileInputStream(new File(context.getFilesDir()+"/"+MODEL_FILE));
        ObjectInputStream ois = new ObjectInputStream(fis);
        model = (Model) ois.readObject();
        fis.close();
        ois.close();
        return model;
    }

    public static AppMember deserializeAppMember(Context context) throws  IOException, ClassNotFoundException {
        AppMember member;
        FileInputStream fis = new FileInputStream(new File(context.getFilesDir()+"/"+APPMEMBER_FILE));
        ObjectInputStream ois = new ObjectInputStream(fis);
        member = (AppMember) ois.readObject();
        fis.close();
        ois.close();
        return member;
    }

    public static void deleteModel(Context context) throws IOException {
        File file = new File(context.getFilesDir()+"/"+MODEL_FILE);
        boolean deleted = file.getCanonicalFile().delete();
        if (!deleted) throw new IOException("Couldn't delete file: "+MODEL_FILE);
    }

    public static void deleteAppMember(Context context) throws  IOException {
        File file = new File(context.getFilesDir()+"/"+APPMEMBER_FILE);
        boolean deleted = file.getCanonicalFile().delete();
        if (!deleted) throw new IOException("Couldn't delete file: "+APPMEMBER_FILE);
    }
}
