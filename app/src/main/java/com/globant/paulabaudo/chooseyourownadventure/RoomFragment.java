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
public class RoomFragment extends Fragment {

    Button mDoor1Button;
    Button mDoor2Button;

    public RoomFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_room, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init(){
        mDoor1Button = (Button) getView().findViewById(R.id.button_door_one);
        mDoor2Button = (Button) getView().findViewById(R.id.button_door_two);

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
                        ResultFragment resultFragmentW = new ResultFragment();
                        passArgumentsToFragment(resultFragmentW, value);
                        insertResultFragment(resultFragmentW);
                        break;
                    case 2:
                        ResultFragment resultFragmentL = new ResultFragment();
                        passArgumentsToFragment(resultFragmentL, value);
                        insertResultFragment(resultFragmentL);
                        break;
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        getFragmentManager().beginTransaction().
                                replace(R.id.frame_adventure, new AlleyFragment()).commit();
                        break;
                    default:
                        getFragmentManager().beginTransaction().
                                replace(R.id.frame_adventure, new RoomFragment()).commit();
                        break;
                }
            }
        };

        mDoor1Button.setOnClickListener(listener);
        mDoor2Button.setOnClickListener(listener);
    }

    private void insertResultFragment(ResultFragment resultFragment) {
        getFragmentManager().beginTransaction().
                replace(R.id.frame_adventure, resultFragment).commit();
    }

    private void passArgumentsToFragment(ResultFragment resultFragment, int value) {
        Bundle resultArguments = new Bundle();
        if (value==1){
            resultArguments.putString(MainActivity.RESULT, MainActivity.RESULT_WIN);
        } else {
            resultArguments.putString(MainActivity.RESULT, MainActivity.RESULT_LOOSE);
        }
        resultFragment.setArguments(resultArguments);
    }

}
