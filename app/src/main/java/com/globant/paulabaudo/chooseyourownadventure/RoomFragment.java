package com.globant.paulabaudo.chooseyourownadventure;


import android.app.FragmentManager;
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
public class RoomFragment extends Fragment {

    Button mDoor1Button;
    Button mDoor2Button;
    int mWin;
    int mLoose;

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
            int value = chance.nextInt(100) + 1;
            setDifficultyValues();
            if (value<=mWin){
                ResultFragment resultFragmentW = new ResultFragment();
                passArgumentsToFragment(resultFragmentW, 1);
                insertResultFragment(resultFragmentW);
            } else {
                if (value>mWin && value<=(mLoose+mWin)){
                    ResultFragment resultFragmentL = new ResultFragment();
                    passArgumentsToFragment(resultFragmentL, 2);
                    insertResultFragment(resultFragmentL);
                } else {
                    if (value>(mLoose+mWin) && value<=60){
                        getFragmentManager().beginTransaction().
                                replace(R.id.frame_adventure, new AlleyFragment()).
                                addToBackStack(null).commit();
                    } else {
                        getFragmentManager().beginTransaction().
                                replace(R.id.frame_adventure, new RoomFragment()).
                                addToBackStack(null).commit();
                    }
                }
            }
            }
        };

        mDoor1Button.setOnClickListener(listener);
        mDoor2Button.setOnClickListener(listener);
    }

    private void setDifficultyValues() {
        if (StartFragment.DIFFICULTY.equals(StartFragment.DIFFICULTY_LOW)){
           mWin = StartFragment.DIFFICULTY_LOW_VALUE;
            mLoose = StartFragment.DIFFICULTY_HIGH_VALUE;
        } else {
            if (StartFragment.DIFFICULTY.equals(StartFragment.DIFFICULTY_MEDIUM)){
                mWin = StartFragment.DIFFICULTY_MEDIUM_VALUE;
                mLoose = StartFragment.DIFFICULTY_MEDIUM_VALUE;
            } else {
                mWin = StartFragment.DIFFICULTY_HIGH_VALUE;
                mLoose = StartFragment.DIFFICULTY_LOW_VALUE;
            }
        }
    }

    private void insertResultFragment(ResultFragment resultFragment) {
        getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        getFragmentManager().beginTransaction().
                replace(R.id.frame_adventure, resultFragment).
                commit();
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
