package com.jordimontornes.megawififlasher.views.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jordimontornes.megawififlasher.R;

public class SettingsFragment extends Fragment {

    private OnSettingsFragmentInteractionListener settingsFragmentListener;

    public SettingsFragment() {

    }


    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSettingsFragmentInteractionListener) {
            settingsFragmentListener = (OnSettingsFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSettingsFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        settingsFragmentListener = null;
    }


    public void onButtonPressed(Uri uri) {
        if (settingsFragmentListener != null) {
            settingsFragmentListener.onFragmentInteraction2(uri);
        }
    }

    public interface OnSettingsFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction2(Uri uri);
    }
}
