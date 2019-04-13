package com.example.LBGManager;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.LBGManager.Model.AppMember;
import com.example.LBGManager.Model.LBG;
import com.example.LBGManager.Model.Task;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    // This is the list containing all the user tasks
    private final List<Task> tasks = LBG.getUserTasks(AppMember.getMember_id());

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.task_list_cell, parent, false);

        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder taskViewHolder, int position) {
        Task task = tasks.get(position);
        taskViewHolder.display(task);
    }

    @Override
    public int getItemCount() {
        return LBG.getUserTasks(AppMember.getMember_id()).size();
    }

    // This class contains a task element with a view and gets recycled trough the method display(task)
    public static class TaskViewHolder extends RecyclerView.ViewHolder {

        private Task current_tasks;

        private TextView date;
        private TextView name;

        public TaskViewHolder(View itemView) {
            super(itemView);

            date = (TextView) itemView.findViewById(R.id.my_task_date);
            name = (TextView) itemView.findViewById(R.id.my_task_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO: Switch to a new activity with the Task
                }
            });

        }

        public void display(Task task) {
            current_tasks = task;
            date.setText(current_tasks.getDeadline().toString()); //TODO: Convert Date to a certain string
            name.setText(current_tasks.getName());
        }
    }

}
