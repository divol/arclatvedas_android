package com.alv.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
//http://stackoverflow.com/questions/7977392/android-dialogfragment-vs-dialog/21032871#21032871


//usage
//DialogFragment dialog = new YesNoDialog();
//Bundle args = new Bundle();
//args.putString("title", title);
//args.putString("message", message);
//dialog.setArguments(args);
//dialog.setTargetFragment(this, YES_NO_CALL);
//dialog.show(getFragmentManager(), "tag");


public class YesNoDialog extends DialogFragment{
	public YesNoDialog()
    {

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        Bundle args = getArguments();
        String title = args.getString("title", "");
        String message = args.getString("message", "");

        return new AlertDialog.Builder(getActivity())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, null);
                }
            })
            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_CANCELED, null);
                }
            })
            .create();
    }
}
