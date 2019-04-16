package com.example.LBGManager;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.LBGManager.Model.AppMember;
import com.example.LBGManager.Model.LBG;
import com.example.LBGManager.Model.Event;
import java.util.List;
import android.net.Uri;


public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> implements Channel {

    // This is the list containing all the user tasks
    private List<Event> events = LBG.getEvents();

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LBG.addObserver(this); // So it will be notified when state changes

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.event_list_cell, parent, false);


        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder eventViewHolder, int position) {
        Event event = events.get(position);
        eventViewHolder.display(event);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    @Override
    public void stateChanged() {
        events = LBG.getEvents();
        notifyDataSetChanged();
    }


    // This class contains a task element with a view and gets recycled trough the method display(task)
    public static class EventViewHolder extends RecyclerView.ViewHolder {


        private static final String LOG = "TaskViewHolder";
        private Event current_event;

        private TextView date;
        private TextView name;
        private ImageView imageView;

        public EventViewHolder(final View itemView) {
            super(itemView);

            date = (TextView) itemView.findViewById(R.id.my_event_date);
            name = (TextView) itemView.findViewById(R.id.my_event_title);
            imageView = (ImageView) itemView.findViewById(R.id.my_event_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO: Switch to a new activity with the Task
                    /*
                    Intent intent = new Intent(itemView.getContext(), EventActivity.class);
                    intent.putExtra("EVENT_ID", current_event.getId());
                    itemView.getContext().startActivity(intent);
                     */
                }
            });

        }

        public void display(Event event) {
            current_event = event;
            date.setText("Mon. \n17 oct."); //TODO: Convert Date to a certain string
            name.setText(event.getName());
            imageView.setImageResource(R.drawable.best_logo);
        }
    }

}
