package com.globant.paulabaudo.chooseyourownadventure;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlleyFragment extends Fragment {

    Button mLeftButton;
    Button mRightButton;
    Button mContinueButton;

    public AlleyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_alley, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init(){
        mLeftButton = (Button) getView().findViewById(R.id.button_go_left);
        mRightButton = (Button) getView().findViewById(R.id.button_go_right);
        mContinueButton = (Button) getView().findViewById(R.id.button_continue);

        setListeners();
    }

    private void setListeners(){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random chance = new Random();
                int value = chance.nextInt(10) + 1;
                switch (value){
                    case 1:
                        Intent iWin = new Intent(getActivity(),ResultActivity.class);
                        iWin.putExtra("result",1);
                        startActivity(iWin);
                        break;
                    case 2:
                        Intent iLoose = new Intent(getActivity(),ResultActivity.class);
                        iLoose.putExtra("result", 2);
                        startActivity(iLoose);
                        break;
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        Intent iAlley = new Intent(getActivity(),AlleyActivity.class);
                        startActivity(iAlley);
                        break;
                    default:
                        Intent iRoom = new Intent(getActivity(),RoomActivity.class);
                        startActivity(iRoom);
                        break;
                }
            }
        };

        mLeftButton.setOnClickListener(listener);
        mRightButton.setOnClickListener(listener);
        mContinueButton.setOnClickListener(listener);
    }
}
