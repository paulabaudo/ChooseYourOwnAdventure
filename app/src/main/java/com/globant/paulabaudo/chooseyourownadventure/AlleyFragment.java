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

        mLeftButton.setOnClickListener(listener);
        mRightButton.setOnClickListener(listener);
        mContinueButton.setOnClickListener(listener);
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
