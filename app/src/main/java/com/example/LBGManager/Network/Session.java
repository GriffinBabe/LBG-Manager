package com.example.LBGManager.Network;

import com.example.LBGManager.Model.*;
import com.example.LBGManager.Network.Exceptions.InvalidResponseFormat;
import com.example.LBGManager.Network.Exceptions.WrongTokenException;
import com.example.LBGManager.Network.Exceptions.InvalidPasswordException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * This class creates and stores the account token necessary for requestes
 */
public class Session {

    private static String token;
    private static Session INSTANCE;
    private static HttpURLConnection connection;
    private static String link = "http://10.0.2.2:8000/api/";
    private static SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZZZZZ");

    private Session(String username, String password) throws Exception {
        if (token == null) {
            createToken(username, password);
        }
    }

    private Session(String token) {
        Session.token = token;
    }

    public static Session getInstance(String username, String password) throws  Exception {
        if (INSTANCE==null) {
            INSTANCE = new Session(username, password);
        }
        return INSTANCE;
    }

    public static Session getInstance(String token) {
        if (INSTANCE==null) {
            INSTANCE = new Session(token);
        }
        return INSTANCE;
    }

    public void createToken(String username, String password) throws InvalidResponseFormat, InvalidPasswordException,
            IOException, JSONException {
        connect();
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("request_type", "get_authentication_key");
        jsonRequest.put("id", username);
        jsonRequest.put("password", password);
        writeToConnection(jsonRequest);
        JSONObject jsonResponse = readToConnection();
        disconnect();
        if (!jsonResponse.getString("request_status").equals("ok")) {
            throwInvalidResponseFormat(jsonRequest);
        }
        if (jsonResponse.getString("password").equals("ok")) {
            token = jsonResponse.getString("token");
        } else {
            throwInvalidPassword();
        }
    }

    public Model gatherModel() throws Exception {
        connect();
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("request_type", "get_model");
        jsonRequest.put("auth_key", token);
        writeToConnection(jsonRequest);
        JSONObject jsonResponse = readToConnection();
        disconnect();
        String request_status = jsonResponse.getString("request_status");
        if (request_status.equals("no_auth") || request_status.equals("error")) {
            throwInvalidResponseFormat(jsonRequest);
        } else if (request_status.equals("wrong_auth")) {
            throwWrongToken();
        }
        return loadModel(jsonResponse);
    }

    public Member checkToken() throws IOException, WrongTokenException, InvalidResponseFormat, JSONException {
        connect();
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("request_type", "check_token");
        jsonRequest.put("auth_key", token);
        writeToConnection(jsonRequest);
        JSONObject jsonResponse = readToConnection();
        disconnect();
        String request_status = jsonResponse.getString("request_status");
        if (request_status.equals("wrong_auth")) {
            throwWrongToken();
        } else if (request_status.equals("ok")) {
            String id = jsonResponse.getString("id");
            String name = jsonResponse.getString("name");
            String responsibility = jsonResponse.getString("responsibility");
            Boolean administrator = jsonResponse.getBoolean("administrator");
            Member temp_member = new Member(name, id);
            temp_member.setResponsibility(Responsibility.valueOf(responsibility));
            temp_member.setAdministrator(administrator);
            return temp_member;
        }
        throw new InvalidResponseFormat("Check Token received invalid \"request_status\" JSON response "+jsonResponse.toString());
    }

    private Model loadModel(JSONObject response) throws Exception {
        Model model = new Model();
        JSONArray task_json_array = response.getJSONArray("tasks");
        JSONArray event_json_array = response.getJSONArray("events");
        JSONArray member_json_array = response.getJSONArray("members");
        List<Task> tasks = new ArrayList<>();
        List<Event> events = new ArrayList<>();
        List<Member> members = new ArrayList<>();

        for (int i = 0; i < task_json_array.length(); i++) {
            JSONObject task = task_json_array.getJSONObject(i);
            String id = task.getString("id");
            String event_id = task.getString("event_id");
            String name = task.getString("name");
            String description = task.getString("description");
            Date deadline = date_format.parse(task.getString("deadline"));
            List<String> responsibles_ids = new ArrayList<>();
            JSONArray reponsibles_json = task.getJSONArray("responsibles_ids");
            for (int j = 0; j < reponsibles_json.length(); j++) {
                responsibles_ids.add(reponsibles_json.getString(j));
            }
            Task temp_task = new Task(id, event_id, name, description, deadline);
            temp_task.setResponsibles_ids(responsibles_ids);
            tasks.add(temp_task);
        }


        for (int i = 0; i < event_json_array.length(); i++) {
            JSONObject event = event_json_array.getJSONObject(i);
            String id = event.getString("id");
            String name = event.getString("name");
            String description = event.getString("description");
            String main_organiser_id = event.getString("main_organiser_id");
            Date date_begin = date_format.parse(event.getString("date_begin"));
            Date date_end = date_format.parse(event.getString("date_end"));
            List<String> admins_ids = new ArrayList<>();
            JSONArray admins_json = event.getJSONArray("admins_ids");
            for (int j = 0; j < admins_json.length(); j++) {
                admins_ids.add(admins_json.getString(j));
            }
            List<String> tasks_ids = new ArrayList<>();
            JSONArray tasks_json = event.getJSONArray("tasks_ids");
            for (int t = 0; t < tasks_json.length(); t++) {
                tasks_ids.add(tasks_json.getString(t));
            }
            List<String> organisers_ids = new ArrayList<>();
            JSONArray organisers_json = event.getJSONArray("organisers_ids");
            for (int o = 0; o < organisers_json.length(); o++) {
                organisers_ids.add(organisers_json.getString(o));
            }
            Event temp_event = new Event(id, name, description, main_organiser_id, date_begin, date_end);
            temp_event.setOrganisers_ids(organisers_ids);
            temp_event.setAdmins_ids(admins_ids);
            temp_event.setTasks_ids(tasks_ids);
            events.add(temp_event);
        }

        for (int i = 0; i < member_json_array.length(); i++) {
            JSONObject member = member_json_array.getJSONObject(i);
            String id = member.getString("id");
            String name = member.getString("name");
            String responsibility = member.getString("responsibility");
            Boolean administrator = member.getBoolean("administrator");
            Member temp_member = new Member(name, id);
            temp_member.setResponsibility(Responsibility.valueOf(responsibility));
            temp_member.setAdministrator(administrator);
            members.add(temp_member);
        }

        model.setEvents(events);
        model.setMembers(members);
        model.setTasks(tasks);
        return model;
    }

    private void writeToConnection(JSONObject json) throws IOException {
        DataOutputStream os = new DataOutputStream(connection.getOutputStream());
        os.writeBytes(json.toString());
        os.flush();
        os.close();
    }

    private JSONObject readToConnection() throws IOException, JSONException {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return new JSONObject(response.toString());
    }

    private static void connect() throws IOException {
        URL url = new URL(link);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        //connection.connect();
    }

    private static void disconnect() {
        connection.disconnect();
    }

    private static void throwInvalidResponseFormat(JSONObject request) throws InvalidResponseFormat {
        System.out.println("--------------[With This request]---------------");
        System.out.println(request.toString());
        throw new InvalidResponseFormat("Response is not correctly formatted");
    }


    private static void throwWrongToken() throws WrongTokenException {
        throw new WrongTokenException("Wrong token");
    }

    private static void throwInvalidPassword() throws InvalidPasswordException {
        throw new InvalidPasswordException("Wrong password");
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        Session.token = token;
    }
}
