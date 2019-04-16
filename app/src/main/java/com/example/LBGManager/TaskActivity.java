package com.example.LBGManager;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.LBGManager.Model.LBG;
import com.example.LBGManager.Model.Task;

public class TaskActivity extends AppCompatActivity {

    private Task task;
    private TextView task_title;
    private TextView task_deadline;
    private TextView task_description;
    private TextView task_responsibles;
    private Button done_button;
    private Button dodge_button;
    private Button discussion_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        Toolbar toolbar = findViewById(R.id.task_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Gets the task from the ID passed in the intent
        String task_id = getIntent().getStringExtra("TASK_ID");
        task = LBG.getTaskById(task_id);

        setTitle(task.getName());

        task_title = (TextView) findViewById(R.id.task_title);
        task_deadline = (TextView) findViewById(R.id.task_deadline);
        task_description = (TextView) findViewById(R.id.task_description);
        task_responsibles = (TextView) findViewById(R.id.task_responsibles);

        done_button = (Button) findViewById(R.id.task_done_button);
        dodge_button = (Button) findViewById(R.id.task_dodge_button);
        discussion_button = (Button) findViewById(R.id.task_discussion_button);

        done_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: send to server that task has been done
                System.out.println("Done button on click");
            }
        });

        dodge_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: create the dodge interface
                System.out.println("Dodge button on click");
                DodgeDialog dodgeDialog = new DodgeDialog();
                dodgeDialog.show(getSupportFragmentManager().beginTransaction(), "dialog");
            }
        });

        discussion_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: deep-linking to correct slack channel
                System.out.println("Discussion button on click");
            }
        });

        changeInformations();
    }

    private void changeInformations() {
        task_title.setText(task.getName());
        task_deadline.setText("Monday 12 oct."); //TODO:Set deadline with java date
        task_deadline.setText(task.getDeadline().toString());
        task_description.setText(task.getDescription());

        String total_responisbles_names = "";
        for (String member_id : task.getResponsibles()) {
            String name = LBG.getMemberById(member_id).getName();
            total_responisbles_names = total_responisbles_names.concat(name);
        }
        String final_names = "Responsibles: "+total_responisbles_names;
        task_responsibles.setText(final_names);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }

    public Task getTask() {
        return task;
    }
}
