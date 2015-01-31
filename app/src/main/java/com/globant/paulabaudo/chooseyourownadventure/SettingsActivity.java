package com.globant.paulabaudo.chooseyourownadventure;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

/**
 * Created by Paula on 31/01/2015.
 */
public class SettingsActivity extends Activity {

    public SettingsActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getFragmentManager().beginTransaction().replace(R.id.frame_settings, new SettingsFragment()).
                commit();
    }
}
