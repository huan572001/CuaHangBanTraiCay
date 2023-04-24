package com.example.cuahangbantraicay.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cuahangbantraicay.API.ProductAPI;
import com.example.cuahangbantraicay.API.UserAPI;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.Utils.VolleyCallback;

import org.json.JSONException;
import org.json.JSONObject;

public class Profile extends AppCompatActivity {
    int idUser = 1;
    Button edit;
    TextView tv_fname, tv_phone, tv_gender, tv_birthday, tv_email;
    String name, email ,phone, birthday;
    Integer gender;

    public void setControl(){
        edit = findViewById(R.id.edit_profile);
        tv_fname = findViewById(R.id.tv_fname);
        tv_birthday = findViewById(R.id.tv_birthday);
        tv_phone = findViewById(R.id.tv_phone);
        tv_gender = findViewById(R.id.tv_gender);
        tv_email = findViewById(R.id.tv_email);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);
        setControl();
        getUser();
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, EditProfile.class);
                startActivity(intent);
            }
        });
    }
    private void getUser(){
        try {
            UserAPI.getUser(this, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject result) {
                    try {
                        if((Boolean)result.get("success")){
                            JSONObject data = result.getJSONObject("data");
                            name  = data.getString("name");
                            gender  = data.getInt("gender");
                            email  = data.getString("email");
                            phone  = data.getString("phone");
                            birthday  = data.getString("birthday");

                            tv_fname.setText(name);
                            if(gender == 1){
                                tv_gender.setText("Nam");
                            }else tv_gender.setText("Nữ");
                            tv_email.setText(email);
                            tv_phone.setText(phone);
                            tv_birthday.setText(birthday);
                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override
                public void onError(JSONObject errorMessage) {

                }
            },idUser);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
