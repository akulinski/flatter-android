package com.kpg.flatter.core;

import android.content.Context;
import android.content.SharedPreferences;

import com.kpg.flatter.R;
import com.kpg.flatter.core.exceptions.SharedPreferenceValueNotFoundException;

import javax.inject.Inject;

public final class SharedPreferencesWraper {

    private Context context;

    private SharedPreferences sharedPreferences;

    @Inject
    public SharedPreferencesWraper(Context context) {

        this.context = context;
        this.sharedPreferences = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    }

    public void addStringToPreferences(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void removeStringCredentials(String key){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }

    public String readStringFromPreferences(String key) throws SharedPreferenceValueNotFoundException {
        SharedPreferences sharedPref = sharedPreferences;

        String returnValue = sharedPref.getString(key, null);
        if (returnValue == null) {
            throw new SharedPreferenceValueNotFoundException(key);
        }

        return returnValue;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }
}
