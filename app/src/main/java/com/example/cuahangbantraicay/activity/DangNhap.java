package com.example.cuahangbantraicay.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cuahangbantraicay.API.LoginAPI;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.Utils.BASE_URL;
import com.example.cuahangbantraicay.Utils.CustomToast;
import com.example.cuahangbantraicay.Utils.VolleyCallback;

import org.json.JSONException;
import org.json.JSONObject;

public class DangNhap extends AppCompatActivity {
    Button DangNhap;
    TextView QuenMK,DK;
    EditText UserName,Password;
    SharedPreferences sharedPreferences;
    @SuppressLint("UseCompatLoadingForDrawables")
    private  void setControl() {
        DangNhap=findViewById(R.id.DN);
        UserName=findViewById(R.id.username);
        Password=findViewById(R.id.password);
        QuenMK=findViewById(R.id.quenMK);
        DK=findViewById(R.id.DK);
//        eye=getResources().getDrawable(R.drawable.baseline_remove_red_eye_24);
//        eye.setBounds(0, 0, eye.getIntrinsicWidth(), eye.getIntrinsicHeight());
//        UserName.setCompoundDrawables(eye,null,null,null);

    }
    private void setEvent(){
        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);

        DangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(UserName.getText().toString())){
                    UserName.setError("Không được để trống!");
                    UserName.requestFocus();
                }
//                else if (!Patterns.EMAIL_ADDRESS.matcher(UserName.getText().toString()).matches()) {
//                    UserName.setError("Nhập sai định dạng email");
//                    UserName.requestFocus();
//                }
                else if(TextUtils.isEmpty(Password.getText().toString())){
                    Password.setError("Không được để trống!");
                    Password.requestFocus();
                }
                else {
                    try {
                        login();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }


//                Intent intent = new Intent(DangNhap.this, MainActivity.class);
//                startActivity(intent);
            }
        });

        DK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangNhap.this, DangKy.class);
                startActivity(intent);
            }
        });
        QuenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangNhap.this, com.example.cuahangbantraicay.activity.QuenMK.class);
                startActivity(intent);
            }
        });
    }
    public  void login() throws JSONException {
        LoginAPI.Login(DangNhap.this, new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    if((Boolean) result.get("success")){
                        setToken((String) result.get("token"));
                        Intent intent = new Intent(DangNhap.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else {
//                        Toast.makeText(getApplicationContext(), result.get("mgs").toString(), Toast.LENGTH_SHORT).show();
                        CustomToast.makeText(getApplicationContext(), "Tên đăng nhập hoặc mật khẩu không đúng !", CustomToast.LENGTH_SHORT, CustomToast.ERROR, true).show();
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }

            @Override
            public void onError(JSONObject errorMessage) {
                System.out.println(errorMessage);
            }
        },UserName.getText().toString(),Password.getText().toString());
    }
    private void setToken(String token) {

        SharedPreferences.Editor editer=sharedPreferences.edit();
        editer.putString("token",token);
        editer.commit();
    }
    private void CheckLogin(){
        String token = sharedPreferences.getString("token", null);
        if(token!=null){
            Intent intent = new Intent(DangNhap.this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.dang_nhap);
        setControl();
        setEvent();
        CheckLogin();


    }
}