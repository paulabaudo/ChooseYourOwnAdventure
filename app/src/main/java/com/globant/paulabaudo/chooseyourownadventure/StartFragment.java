package com.globant.paulabaudo.chooseyourownadventure;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class StartFragment extends Fragment {

    Button mStartButton;

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
                Random randomScreen = new Random();
                int screen = randomScreen.nextInt(2);
                if (screen == 0){
                    getFragmentManager().beginTransaction().
                            replace(R.id.frame_adventure, new AlleyFragment()).
                            addToBackStack(getClass().getName()).commit();
                } else {
                    getFragmentManager().beginTransaction().
                            replace(R.id.frame_adventure, new RoomFragment()).
                            addToBackStack(getClass().getName()).commit();
                }
            }
        });
    }

}
