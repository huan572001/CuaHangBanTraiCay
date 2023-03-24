package com.example.cuahangbantraicay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cuahangbantraicay.API.LoginAPI;

public class DangKy extends AppCompatActivity {
    TextView DN;
    Button btnDK;
    private  void setControl() {
        DN=findViewById(R.id.linkDN);
        btnDK=findViewById(R.id.btnDK);
    }
    private void setEvent(){
        DN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangKy.this, com.example.cuahangbantraicay.DangNhap.class);
                startActivity(intent);
            }
        });
        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangKy.this, com.example.cuahangbantraicay.OTP.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangky);
        setControl();
        setEvent();
    }
}