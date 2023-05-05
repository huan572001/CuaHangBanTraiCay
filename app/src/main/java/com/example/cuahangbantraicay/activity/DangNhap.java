package com.example.cuahangbantraicay.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cuahangbantraicay.API.LoginAPI;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.Utils.CustomToast;
import com.example.cuahangbantraicay.Utils.VolleyCallback;

import org.json.JSONException;
import org.json.JSONObject;

public class DangNhap extends AppCompatActivity {
    Button DangNhap,btnCart,btnOrder,btnStatistic;
    TextView QuenMK,DK;
    EditText UserName,Password;
    SharedPreferences sharedPreferences;
    @SuppressLint("UseCompatLoadingForDrawables")
    private  void setControl() {
        btnStatistic=findViewById(R.id.facebook);
        btnCart=findViewById(R.id.google);
        btnOrder=findViewById(R.id.apple);
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
        btnStatistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DangNhap.this,StatisticActivity.class));
            }
        });
    }
    public  void login() throws JSONException {
        LoginAPI.Login(DangNhap.this, new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    if((Boolean) result.get("success")){
                        JSONObject data= result.getJSONObject("data");
                        Intent intent;
                        if(data.getInt("role")==1){
                             intent = new Intent(DangNhap.this, MainActivity.class);
                        }else {
                            intent = new Intent(DangNhap.this, Admin.class);
                        }
                        setToken((String) result.get("token"),data.getInt("role"));
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
    private void setToken(String token,int role) {

        SharedPreferences.Editor editer=sharedPreferences.edit();
        editer.putString("token",token);
        editer.putInt("role",role);
        editer.commit();
    }
    private void CheckLogin(){
        String token = sharedPreferences.getString("token", null);
        int role= sharedPreferences.getInt("role",-1);
        if(token!=null){
            Intent intent;
            if(role==1){
                intent = new Intent(DangNhap.this, MainActivity.class);
            }else {
                intent = new Intent(DangNhap.this, Admin.class);
            }

            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getSupportActionBar().hide();
        setContentView(R.layout.dang_nhap);
        setControl();
        setEvent();
        CheckLogin();


    }
}