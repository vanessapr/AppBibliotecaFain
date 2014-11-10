package com.vanessapr.appbibliotecafain.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Message;
import android.os.Vibrator;
import android.widget.Toast;

import java.io.File;

/**
 * Created by Milagros on 29/10/2014.
 */
public class MessageDialog extends AlertDialog.Builder {
    private Context mContext;
    private boolean retornar = false;

    public MessageDialog(Context context) {
        super(context);
        mContext = context;
    }

    public MessageDialog(Context context, boolean back) {
        super(context);
        mContext = context;
        retornar = back;
    }


    public void display(String message) {
        Vibrator vibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(200);

        this.setTitle("Información");
        this.setMessage(message);
        this.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id) {
                if(retornar)
                    ((Activity) mContext).onBackPressed();
                else
                    dialog.cancel();

            }
        });

        AlertDialog msg = create();
        msg.show();
    }

    public void displayPDF(String message, final File file) {
        Vibrator vibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(200);
        this.setTitle("Información");
        this.setMessage(message);
        this.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Uri path = Uri.fromFile(file);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(path, "application/pdf");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                try {
                    mContext.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(mContext, "No hay ninguna aplicación para abrir el archivo", Toast.LENGTH_LONG).show();
                }
            }
        });

        AlertDialog msg = create();
        msg.show();
    }


}
