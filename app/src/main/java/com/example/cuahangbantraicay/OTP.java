package com.example.cuahangbantraicay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class OTP extends AppCompatActivity {
    TextView textCountDownTimer,resetOTP;
    CountDownTimer CountDownTimer;
    private  void setControl() {
    textCountDownTimer=findViewById(R.id.text_countDown);
    resetOTP=findViewById(R.id.textReset);
    }
    private void setEvent(){
        resetOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CountDownTimer.start();
            }
        });
    }
    private void startTimer(){
        CountDownTimer=new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textCountDownTimer.setText('('+String.valueOf(millisUntilFinished/1000)+"s)");

            }

            @Override
            public void onFinish() {
            }
        };
        CountDownTimer.start();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp);
        setControl();
        setEvent();
        startTimer();
    }
}