package com.vanessapr.appbibliotecafain.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Message;
import android.os.Vibrator;

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


}
