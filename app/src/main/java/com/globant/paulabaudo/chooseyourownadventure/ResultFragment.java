package com.globant.paulabaudo.chooseyourownadventure;


import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {

    TextView mResultText;
    final static String BRAVE_FIGHTER = "Brave Fighter";
    final static String WIN_MESSAGE_PREF = "win_message_preference";
    final static String LOSE_MESSAGE_PREF = "lose_message_preference";

    public ResultFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init(){
        mResultText = (TextView) getView().findViewById(R.id.text_view_result);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String winnerMessage = BRAVE_FIGHTER + ": " + sharedPreferences.getString(WIN_MESSAGE_PREF,
                getResources().getString(R.string.text_win));
        String loserMessage = BRAVE_FIGHTER + ": " + sharedPreferences.getString(LOSE_MESSAGE_PREF,
                getResources().getString(R.string.text_loose));

        if (getArguments().get(MainActivity.RESULT).equals(MainActivity.RESULT_WIN)){
            mResultText.setText(winnerMessage);
        } else {
            mResultText.setText(loserMessage);
        }

    }
}
