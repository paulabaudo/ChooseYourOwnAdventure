package com.globant.paulabaudo.chooseyourownadventure;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by Paula on 31/01/2015.
 */
public class SettingsFragment extends PreferenceFragment {

    public SettingsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}
