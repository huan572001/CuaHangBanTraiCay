package com.example.cuahangbantraicay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cuahangbantraicay.Utils.internet;

public class MainActivity extends AppCompatActivity {
    Button btnDN;
    private  void setControl() {
        btnDN=findViewById(R.id.btnDN);
    }
    private void setEvent(){
        btnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.example.cuahangbantraicay.DangNhap.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        internet.isConnected(this);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();

    }
}