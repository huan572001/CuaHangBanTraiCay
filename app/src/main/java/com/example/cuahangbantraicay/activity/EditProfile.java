package com.example.cuahangbantraicay.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cuahangbantraicay.R;

public class EditProfile extends AppCompatActivity {
    Button btnSaveEdit;
    TextView user_fname, user_phone, user_male,user_female, user_birthday, user_email;

    String name, email, birthday, phone;
    Integer gender;

    public void setControl(){
        btnSaveEdit = findViewById(R.id.btnSaveEdit);
        user_fname = findViewById(R.id.user_fullname);
        user_birthday = findViewById(R.id.user_birthday);
        user_phone = findViewById(R.id.user_phone);
        user_male = findViewById(R.id.user_male);
        user_female = findViewById(R.id.user_female);
        user_email = findViewById(R.id.user_email);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);
        setControl();
        btnSaveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProfile.this, Profile.class);
                startActivity(intent);
            }
        });
    }
}
