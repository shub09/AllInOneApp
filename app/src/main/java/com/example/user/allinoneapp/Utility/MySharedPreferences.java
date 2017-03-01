package com.example.user.allinoneapp.Utility;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by user on 2/11/2017.
 */

public class MySharedPreferences {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String prefName = "MyPrefs";

    public MySharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public String getString(String key){
        return sharedPreferences.getString(key, "");
    }

    public void putString(String key, String value){
        editor.putString(key, value).commit();
    }
}
