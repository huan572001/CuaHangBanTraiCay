package com.example.cuahangbantraicay.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import com.example.cuahangbantraicay.API.AuthAPI;
import com.example.cuahangbantraicay.Modal.User;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.Utils.CustomToast;
import com.example.cuahangbantraicay.Utils.Loadding;
import com.example.cuahangbantraicay.Utils.VolleyCallback;
import com.example.cuahangbantraicay.adapter.OTPAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OTP extends AppCompatActivity {
    TextView textCountDownTimer, resetOTP;
    CountDownTimer CountDownTimer;

    RecyclerView rcvOtp, rcvNumber;
    OTPAdapter otpAdapter, otpNumberAdapter;

    List<String> listOTP = new ArrayList<>();
    List<String> listNumber = new ArrayList<>();
    String tokenOTP;
    User user;
    int count = 0;

    private void createViewProduct() {
        otpAdapter = new OTPAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rcvOtp.setLayoutManager(linearLayoutManager);
        getListDetailATypePoduct();
        rcvOtp.setAdapter(otpAdapter);
    }

    private void createViewNumber() {
        otpNumberAdapter = new OTPAdapter(this);
        GridLayoutManager layoutManagerGrid = new GridLayoutManager(this, 3);


        rcvNumber.setLayoutManager(layoutManagerGrid);
        getListNumber();
        rcvNumber.setAdapter(otpNumberAdapter);
    }

    private void setControl() {
        textCountDownTimer = findViewById(R.id.text_countDown);
        resetOTP = findViewById(R.id.textReset);
        rcvOtp = findViewById(R.id.rcv_OTP);
        rcvNumber = findViewById(R.id.rcv_number_OTP);
    }

    private void setEvent() {
        tokenOTP= (String) getIntent().getSerializableExtra("tokenOTP");
        user=(User) getIntent().getSerializableExtra("rigisterUser");
        System.out.println(user);
        System.out.println(tokenOTP);
        createViewProduct();
        createViewNumber();
        //Nhận giá trị onclick nhận từ adapter
        otpNumberAdapter.setOnItemClickListener(new OTPAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String value) {
                if (value.equals("Xoa")) {
                    if (count > 0) {
                        count--;
                        listOTP.set(count, "0");
                        otpAdapter.setData(listOTP);
                    }
                } else {
                    if (count < 6) {
                        listOTP.set(count, value);
                        count++;
                        otpAdapter.setData(listOTP);
                    }
                    if (count == 6) {
                        String otp="";
                        for (int i = 0; i < listOTP.size(); i++) {
                            otp=otp+listOTP.get(i);
                        }
                        verifyOTP(otp);
                    }
                }
            }
        });

        resetOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CountDownTimer.start();
                for (int i=0;i<listOTP.size();i++){
                    listOTP.set(i,"0");

                }
                otpAdapter.setData(listOTP);
                System.out.println("gọi API gửi lại mã OTP");
            }
        });
    }

    private void startTimer() {
        CountDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textCountDownTimer.setText('(' + String.valueOf(millisUntilFinished / 1000) + "s)");
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

    private void getListDetailATypePoduct() {
        listOTP.add("0");
        listOTP.add("0");
        listOTP.add("0");
        listOTP.add("0");
        listOTP.add("0");
        listOTP.add("0");

        otpAdapter.setData(listOTP);

    }

    private void getListNumber() {
        listNumber.add("1");
        listNumber.add("2");
        listNumber.add("3");
        listNumber.add("4");
        listNumber.add("5");
        listNumber.add("6");
        listNumber.add("7");
        listNumber.add("8");
        listNumber.add("9");
        listNumber.add("0");
        listNumber.add("Xoa");
        otpNumberAdapter.setData(listNumber);

    }
//    public void sendOTP(){
//        try {
//            final Loadding loadingdialog = new Loadding(this);
//            loadingdialog.startLoadingdialog();
//
//            AuthAPI.SendMail(this, new VolleyCallback() {
//                @Override
//                public void onSuccess(JSONObject result) {
//                    try {
//                        if((Boolean) result.get("success")){
//
//
//                        }
//                    } catch (JSONException e) {
//                        loadingdialog.dismissdialog();
//                        throw new RuntimeException(e);
//
//                    }
//
//                }
//
//                @Override
//                public void onError(JSONObject errorMessage) {
//                    loadingdialog.dismissdialog();
//                }
//            },edt_email.getText().toString());
//        } catch (JSONException e) {
//            throw new RuntimeException(e);
//        }
//    }
    private void verifyOTP(String otp){
        final Loadding loadingdialog = new Loadding(this);
        loadingdialog.startLoadingdialog();
        try {

            AuthAPI.VerifiOTP(this, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject result) {
                    try {
                        if((Boolean) result.get("success")){
                            rigister();

                            loadingdialog.dismissdialog();
                        }else {
                            loadingdialog.dismissdialog();
                            CustomToast.makeText(getApplicationContext(), "OTP không đúng !", CustomToast.LENGTH_SHORT, CustomToast.ERROR, true).show();

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
            },otp,tokenOTP);
        } catch (JSONException e) {
            loadingdialog.dismissdialog();
            throw new RuntimeException(e);
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed(); // gọi phương thức gốc
    }
    private void rigister(){
        final Loadding loadingdialog = new Loadding(this);
        loadingdialog.startLoadingdialog();
        try {

            AuthAPI.Rigister(this, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject result) {
                    try {
                        if((Boolean) result.get("success")){
                            CustomToast.makeText(getApplicationContext(), "Dang ky thanh cong !", CustomToast.LENGTH_SHORT, CustomToast.ERROR, true).show();
                            loadingdialog.dismissdialog();
                            Intent intent = new Intent(getBaseContext(), DangNhap.class);
                            startActivity(intent);
                        }else {
                            loadingdialog.dismissdialog();
                            CustomToast.makeText(getApplicationContext(), "email hoặc sdt đã tồn tại !", CustomToast.LENGTH_SHORT, CustomToast.ERROR, true).show();
                            onBackPressed();
                        }
                    } catch (JSONException e) {
                        loadingdialog.dismissdialog();
                        throw new RuntimeException(e);
                    }

                }

                @Override
                public void onError(JSONObject errorMessage) {
                    loadingdialog.dismissdialog();
                    Intent intent = new Intent(getBaseContext(), DangNhap.class);
                    startActivity(intent);
                }
            },user);
        } catch (JSONException e) {
            loadingdialog.dismissdialog();
            throw new RuntimeException(e);
        }
    }
}
