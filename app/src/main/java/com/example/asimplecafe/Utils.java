package com.example.asimplecafe;

import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {
    public static void showToast (Context ctx, String text){
        Toast.makeText(ctx, text, Toast.LENGTH_SHORT).show();
    }

    public static LinearLayout.LayoutParams editMainLinearLayoutWeight(float f) {
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.MATCH_PARENT);
        params.weight = f;
        return params;
    }

    public static void showCallback ( String TAG, long result, String callback ){
        if (result == -1){
            callback = "Failure: " + callback;
        } else callback = "Success: "  + callback;
        Log.e(TAG, "" + callback );
    }

    public static String getDateTime(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd-MM-yyyy hh:mm:ss", Locale.getDefault());
        return dateFormat.format(date);
    }

    public static String chop(String str){
        return str.substring(0, str.length() - 1);
    }

}
