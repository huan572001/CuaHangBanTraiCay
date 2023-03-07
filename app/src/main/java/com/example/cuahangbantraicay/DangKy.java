package com.example.cuahangbantraicay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DangKy extends AppCompatActivity {
    TextView DN;
    private  void setControl() {
        DN=findViewById(R.id.linkDN);
    }
    private void setEvent(){
        DN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangKy.this, com.example.cuahangbantraicay.DangNhap.class);
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