package com.example.LBGManager;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import com.example.LBGManager.Model.LBG;
import com.example.LBGManager.Model.Task;

public class TaskActivity extends AppCompatActivity {

    private Task task;
    private TextView task_title;
    private TextView task_deadline;
    private TextView task_description;
    private Button done_button;
    private Button dodge_button;
    private Button discussion_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        Toolbar toolbar = findViewById(R.id.settings_toolbar);
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

        done_button = (Button) findViewById(R.id.task_done_button);
        done_button = (Button) findViewById(R.id.task_dodge_button;
        done_button = (Button) findViewById(R.id.task_done_button);

        changeInformations();
    }

    private void changeInformations() {
        task_title.setText(task.getName());
        task_deadline.setText("Monday 12 oct."); //TODO:Set deadline with java date
        task_description.setText(task.getDescription());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }
}
