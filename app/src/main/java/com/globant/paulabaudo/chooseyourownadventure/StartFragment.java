package com.globant.paulabaudo.chooseyourownadventure;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class StartFragment extends Fragment {

    Button mStartButton;
    public static String DIFFICULTY;
    public final static int DIFFICULTY_LOW_VALUE = 15;
    public final static int DIFFICULTY_MEDIUM_VALUE = 10;
    public final static int DIFFICULTY_HIGH_VALUE = 5;
    public final static String DIFFICULTY_LOW = "LOW";
    public final static String DIFFICULTY_MEDIUM = "MEDIUM";
    public final static String DIFFICULTY_HIGH = "HIGH";

    public StartFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_start, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init(){
        mStartButton = (Button) getView().findViewById(R.id.button_start_traveling);

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDifficulty();          
                Random randomScreen = new Random();
                int screen = randomScreen.nextInt(2);
                if (screen == 0){
                    getFragmentManager().beginTransaction().
                            replace(R.id.frame_adventure, new AlleyFragment()).
                            addToBackStack(null).commit();
                } else {
                    getFragmentManager().beginTransaction().
                            replace(R.id.frame_adventure, new RoomFragment()).
                            addToBackStack(null).commit();
                }
            }
        });
    }

    private void getDifficulty() {
        RadioGroup difficultyGroup = (RadioGroup) getView().findViewById(R.id.radio_group_difficulty);

        int id = difficultyGroup.getCheckedRadioButtonId();
        switch (id){
            case R.id.radio_button_low:
                DIFFICULTY = DIFFICULTY_LOW;
                break;
            case R.id.radio_button_medium:
                DIFFICULTY = DIFFICULTY_MEDIUM;
                break;
            case R.id.radio_button_high:
                DIFFICULTY = DIFFICULTY_HIGH;
                break;
        }
    }

}
