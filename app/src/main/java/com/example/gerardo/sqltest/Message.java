package com.example.gerardo.sqltest;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

/**
 * Created by MasterHardisk on 30/06/16.
 */

//clase para mostrar mensajes solamente
public class Message {

    public static void message(View view, String message)
    {

        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }
}
