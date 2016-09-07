package com.sdf.www.popularmovies;

import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SettingFragment extends PreferenceFragment
        implements Preference.OnPreferenceChangeListener{

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);

    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);
        bindPreferenceSmmaryToValue(findPreference(getString(R.string.pref_way_key)));
    }

    private void bindPreferenceSmmaryToValue(Preference preference) {
        preference.setOnPreferenceChangeListener(this);
        onPreferenceChange(preference, PreferenceManager.getDefaultSharedPreferences(preference.getContext())
        .getString(preference.getKey(),""));
    }


    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        if (preference instanceof ListPreference){
            ListPreference listPreference = (ListPreference) preference;
            int pefIndex = listPreference.findIndexOfValue(o.toString());
            if(pefIndex>=0){
                preference.setSummary(o.toString());
            }
        }
        return true;
    }
}
