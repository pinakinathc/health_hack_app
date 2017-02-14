package com.health_hack.www.healthhack;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by HP on 10-Feb-17.
 */
public class MyToastMessage {
    public static void myMessage(Context con, String str) {
        Toast.makeText(con,str, Toast.LENGTH_SHORT).show();
    }
}

