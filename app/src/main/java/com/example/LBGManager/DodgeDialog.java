package com.example.LBGManager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.Spinner;

public class DodgeDialog extends DialogFragment {

    private Spinner spinner;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Pass null as parent view as it's a dialog
        builder.setView(inflater.inflate(R.layout.dodge_dialog,null));
        builder.setPositiveButton("Excuse", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO: Sends the dodge request
            }
        });
        builder.setNegativeButton("Cancer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Closes the dialog
                DodgeDialog.this.getDialog().cancel();
            }
        });

        spinner = (Spinner) getView().findViewById(R.id.dodge_dialog_spinner);

        return builder.create();
    }
}
