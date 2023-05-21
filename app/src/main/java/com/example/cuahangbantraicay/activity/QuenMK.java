package com.example.cuahangbantraicay.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cuahangbantraicay.API.AuthAPI;
import com.example.cuahangbantraicay.Modal.User;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.Utils.CustomToast;
import com.example.cuahangbantraicay.Utils.Loadding;
import com.example.cuahangbantraicay.Utils.VolleyCallback;

import org.json.JSONException;
import org.json.JSONObject;

public class QuenMK extends AppCompatActivity {
    Button btnQMK;
    TextView tv_email_QMK;
    private  void setControl() {
    btnQMK=findViewById(R.id.bntQMK);
    tv_email_QMK=findViewById(R.id.tv_email_QMK);
    }
    private void setEvent(){
        btnQMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOTP();
//                Intent intent = new Intent(QuenMK.this, OTP.class);
//                startActivity(intent);
            }
        });

    }
    public void sendOTP(){
        try {
            final Loadding loadingdialog = new Loadding(this);
            loadingdialog.startLoadingdialog();
            AuthAPI.SendMail(this, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject result) {
                    try {
                        if((Boolean) result.get("success")){



                        }else {
                            tv_email_QMK.setError("email không tồn tại");
                            tv_email_QMK.requestFocus();

                        }
                    } catch (JSONException e) {
                        loadingdialog.dismissdialog();
                        throw new RuntimeException(e);
                    }

                }

                @Override
                public void onError(JSONObject errorMessage) {
                    loadingdialog.dismissdialog();
                }
            },tv_email_QMK.getText().toString());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quen_mk);
        setControl();
        setEvent();
    }
}