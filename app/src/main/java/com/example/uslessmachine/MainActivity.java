package com.example.uslessmachine;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button buttonSelfDestruct;
    private Switch switchUseless;
    private ConstraintLayout constraintLayout;

    public int count;
    public static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        count = 5000;
        wireWidgets();
        setListeners();


    }

    private void setListeners() {
        //TODO self destruct button
        switchUseless.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(MainActivity.this, "ON", Toast.LENGTH_SHORT).show();
                    startSwitchOffTimer();

                }
            }
        });
    }

    private void startSwitchOffTimer() {
        new CountDownTimer(count, 2500) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(!switchUseless.isChecked()){
                    Log.d(TAG, "onTick: canceling");
                }
            }

            @Override
            public void onFinish() {
                changeTime();
                switchUseless.setChecked(false);
                Log.d(TAG, "onFinish; switching to false");
            }
        }.start();

    }


    private void changeTime() {
        if(count > 750){
            count= count/3;
        }
    }

    private void wireWidgets() {
        buttonSelfDestruct = findViewById(R.id.button_main_selfdestruct);
        switchUseless = findViewById(R.id.switch_main_useless);
    }

}
