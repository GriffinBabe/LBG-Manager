package com.example.LBGManager;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import com.example.LBGManager.Model.*;
import com.example.LBGManager.Network.Exceptions.InvalidPasswordException;
import com.example.LBGManager.Network.Session;


public class LoginActivity extends AppCompatActivity {

    private TextView login_text;
    private EditText login;
    private EditText password;
    private Button loginButton;
    private Button discussionButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_text = (TextView) findViewById(R.id.login_login_text);
        login = (EditText) findViewById(R.id.login_login_edit);
        password = (EditText) findViewById(R.id.login_password_edit);
        loginButton = (Button) findViewById(R.id.login_login_button);
        discussionButton = (Button) findViewById(R.id.login_discussion_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(login.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
                    login_text.setText("Please enter your username and password.");
                    login_text.setTextColor(getResources().getColor(R.color.red));
                } else {
                        new Thread( new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Session session = Session.getInstance(login.getText().toString(), password.getText().toString());
                                    Member member = session.checkToken();
                                    AppMember app_member = AppMember.getInstance();
                                    app_member.setMember(member);

                                    Model model = Session.getInstance(app_member.getToken()).gatherModel();

                                    // Saves the app_member and the model
                                    Serializer.serialize(app_member, LoginActivity.this);
                                    Serializer.serialize(model, LoginActivity.this);

                                    LBG.updateModel(model);

                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                                    startActivity(intent);
                                } catch (InvalidPasswordException e) {
                                    login_text.setText("Wrong credentials");
                                    login_text.setTextColor(getResources().getColor(R.color.red));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                }
            }
        });

        discussionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : Deep linking to slack
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Do Nothing
    }
}
