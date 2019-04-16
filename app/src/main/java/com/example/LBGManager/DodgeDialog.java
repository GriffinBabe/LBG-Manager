package com.example.LBGManager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;
import com.example.LBGManager.Model.LBG;
import com.example.LBGManager.Model.Task;
import android.view.View;

import java.security.AllPermission;
import java.util.ArrayList;
import java.util.List;

public class DodgeDialog extends DialogFragment {

    private Spinner spinner;
    private EditText editText;
    private TextView textView;
    private List<String> members_ids;
    private List<String> members_names;
    private String event_id;
    private String task_id;

    private View view;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Pass null as parent view as it's a dialog
        view = inflater.inflate(R.layout.dodge_dialog, null);
        builder.setView(view);

        // We create the dialog
        AlertDialog alertDialog = builder.create();
        // As the dialog positive button trigger two OnClickListener methods. One is from DialogInterface, that we
        // nullify here and the other that we define in this DodgeDialog.onResume() method
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", (DialogInterface.OnClickListener) null);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Excuse", (DialogInterface.OnClickListener) null);

        editText = (EditText) view.findViewById(R.id.dodge_dialog_reason);
        textView = (TextView) view.findViewById(R.id.dodge_dialog_text);

        // Takes the activity so we can get the event id
        TaskActivity task_activity = (TaskActivity) getActivity();
        Task task = task_activity.getTask();

        // Registers the id so we can get the members ids and names and also make a request
        task_id = task.getId();
        event_id = task.getEvent_id();



        spinner = (Spinner) view.findViewById(R.id.dodge_dialog_spinner);

        // Initiates the names and ids arrays and gives the
        members_ids = new ArrayList<>();
        members_names = new ArrayList<>();
        members_ids.add("0");
        members_names.add("No Substitute");

        // Gather all members names and member_ids

        /*
        members_ids.addAll(LBG.getAllMembersFromEventId(event_id));
        for (String id : members_ids) {
            members_names.add(LBG.getMemberById(id).getName());
        }*/

        //Creates the spinner adapter and puts into it every Members
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getActivity(), android.R.layout.simple_spinner_item, members_names);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        return alertDialog;
    }

    // Little trick to prevent the positive button to close the dialog automatically
    @Override
    public void onResume() {
        super.onResume();
        AlertDialog alertDialog = (AlertDialog) getDialog();
        Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editText.getText().toString())) {
                    textView.setText("Please give a reason.");
                    textView.setTextColor(getResources().getColor(R.color.red));
                } else {
                    // TODO: Sends the dodge request
                }
            }
        });

        Button negativeButton = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Closes the dialog
                DodgeDialog.this.getDialog().cancel();
            }
        });
    }
}
