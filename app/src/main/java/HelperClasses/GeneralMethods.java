package HelperClasses;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * Created by user on 11/30/2016.
 */

public class GeneralMethods {
    public void displayToastToUser(Context context, String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

    public void showLocationDialog(Context context, String title, String message, String positive_label, String negative_negative) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
/*
        String positiveText = positive_label;
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // positive button logic
                    }
                });  */

        String negativeText = negative_negative;
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // negative button logic
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }

    public  ProgressDialog showProgressDialog(Context context, String title, String message){
        final ProgressDialog ringProgressDialog = ProgressDialog.show(context, title, "Please wait ...", true);
        ringProgressDialog.setCancelable(false);
        return ringProgressDialog;

    }


}
