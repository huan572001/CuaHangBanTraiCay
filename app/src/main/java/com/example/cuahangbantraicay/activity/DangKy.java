package com.example.cuahangbantraicay.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cuahangbantraicay.R;

public class DangKy extends AppCompatActivity {
    TextView DN;
    Button btnDK;
    EditText edt_fullname,edt_email,edt_phone,edt_password,edt_confirm_password;
    private  void setControl() {
        DN=findViewById(R.id.linkDN);
        btnDK=findViewById(R.id.btnDK);
        edt_fullname=findViewById(R.id.edt_fullName);
        edt_email=findViewById(R.id.edt_emailDK);
        edt_phone=findViewById(R.id.edt_SDT);
        edt_password=findViewById(R.id.edt_MKDK);
        edt_confirm_password=findViewById(R.id.edt_XNMK);
    }
    private void setEvent(){
        DN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangKy.this, DangNhap.class);
                startActivity(intent);
            }
        });
        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(edt_fullname.getText().toString())){
                    edt_fullname.setError("Không được để trống!");
                    edt_fullname.requestFocus();
                } else if (TextUtils.isEmpty(edt_email.getText().toString())) {
                    edt_email.setError("Không được để trống!");
                    edt_email.requestFocus();
                }else if (TextUtils.isEmpty(edt_phone.getText().toString())) {
                    edt_phone.setError("Không được để trống!");
                    edt_phone.requestFocus();
                }else if (TextUtils.isEmpty(edt_password.getText().toString())) {
                    edt_password.setError("Không được để trống!");
                    edt_password.requestFocus();
                }else if (TextUtils.isEmpty(edt_confirm_password.getText().toString())) {
                    edt_confirm_password.setError("Không được để trống!");
                    edt_confirm_password.requestFocus();
                }else if (!edt_password.getText().toString().equals(edt_confirm_password.getText().toString())) {
                    edt_confirm_password.setError("Mật khẩu xác nhận không khớp");
                    edt_confirm_password.requestFocus();
                }else {
                    Intent intent = new Intent(DangKy.this, OTP.class);
                    startActivity(intent);
                }

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