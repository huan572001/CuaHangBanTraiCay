package com.example.cuahangbantraicay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QuenMK extends AppCompatActivity {
    Button btnQMK;
    private  void setControl() {
    btnQMK=findViewById(R.id.bntQMK);
    }
    private void setEvent(){
        btnQMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuenMK.this, com.example.cuahangbantraicay.OTP.class);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quen_mk);
        setControl();
        setEvent();
    }
}