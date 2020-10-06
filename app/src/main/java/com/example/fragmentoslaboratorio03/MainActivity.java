package com.example.fragmentoslaboratorio03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements fragmento.OnFragmentInteractionListener {

    private Button mButton;
    private boolean isFragmentDisplayed = false;
    static final String FRAGMENT_STATE = "state of Fragment";
    private int mRadioButtonChoice = 2;
    private int votos[] = new int[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null) {
            isFragmentDisplayed = savedInstanceState.getBoolean(FRAGMENT_STATE);
            if(isFragmentDisplayed){
                mButton.setText(R.string.cerrar);
            }
        }

        mButton = (Button)findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFragmentDisplayed) {
                    displayFragment();
                } else {
                    closeFragment();
                }
            }

        });
    }

    private void closeFragment() {
        FragmentManager managernew = getSupportFragmentManager();
        fragmento fragmentonew = (fragmento) managernew.findFragmentById(R.id.fragment_container);
        if (fragmentonew != null) {
            managernew.beginTransaction().remove(fragmentonew).commit();
            mButton.setText(R.string.abrir);
            isFragmentDisplayed = false;
        }
    }

    private void displayFragment() {
        fragmento fragmentonew = fragmento.newInstance(mRadioButtonChoice);
        FragmentManager managernew = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = managernew.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, fragmentonew).addToBackStack(null).commit();
        mButton.setText(R.string.cerrar);
        isFragmentDisplayed = true;
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean(FRAGMENT_STATE, isFragmentDisplayed);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void votarButton(int Choice) {
        if(Choice == 0) {
            votos[0]++;
        }else {
            votos[1]++;
        }
        Toast.makeText(this, "Capitan America Tiene "+votos[0]+"\n"+"Iron Man Tiene "+votos[1], Toast.LENGTH_SHORT).show();
    }
}