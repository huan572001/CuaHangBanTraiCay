package com.example.cuahangbantraicay.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cuahangbantraicay.API.UserAPI;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.Utils.VolleyCallback;

import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class EditProfile extends AppCompatActivity {
    int idUser = 1 ;
    Button btnSaveEdit;
    EditText user_fname, user_phone, user_birthday, user_email;
    TextView tv_kq;
    CheckBox user_male, user_female;
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
        tv_kq = findViewById(R.id.tv_kq);
    }

    public void setEvent(){
        user_male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(user_male.isChecked()) user_female.setChecked(false);
            }
        });
        user_female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(user_female.isChecked()) user_male.setChecked(false);
            }
        });
        user_birthday.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private String yyyymmdd = "YYYYMMDD";
            private Calendar cal = Calendar.getInstance();
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]", "");
                    String cleanC = current.replaceAll("[^\\d.]", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    if (clean.equals(cleanC)) sel--;
                    if (clean.length() < 8){
                        clean = clean + yyyymmdd.substring(clean.length());
                    }else{
                        int year = Integer.parseInt(clean.substring(0,4));
                        int mon  = Integer.parseInt(clean.substring(4,6));
                        int day  = Integer.parseInt(clean.substring(6,8));

                        if(mon > 12) mon = 12;
                        cal.set(Calendar.MONTH, mon-1);

                        year = (year<1900)?1900:(year>2100)?2100:year;
                        cal.set(Calendar.YEAR, year);

                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",year, mon, day);
                    }

                    clean = String.format("%s-%s-%s", clean.substring(0, 4),
                            clean.substring(4, 6),
                            clean.substring(6, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    user_birthday.setText(current);
                    user_birthday.setSelection(sel < current.length() ? sel : current.length());
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {}
        });
        btnSaveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user_male.isChecked()) gender = 1;
                if(user_female.isChecked()) gender = 0;
//                Intent intent = new Intent(EditProfile.this, Profile.class);
//                startActivity(intent);
                RequestQueue requestQueue = Volley.newRequestQueue(EditProfile.this);
                String url = "http://localhost:3000/api/v1/admin/updateUserById/:id";
                StringRequest stringRequest = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tv_kq.setText("Thay đổi thành công"+response);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        tv_kq.setText("Thay đổi thất bại"+ user_birthday.getText().toString());

                    }
                }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError{
                        Map<String, String> data = new HashMap<>();
                        data.put("name", user_fname.getText().toString());
                        data.put("gender", gender.toString());
                        data.put("email", user_email.getText().toString());
                        data.put("phone", user_phone.getText().toString());
                        data.put("birthday", user_birthday.getText().toString());

                        return data;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);
        setControl();
        setEvent();
        getUser();

    }
    private void getUser() {
        try {
            UserAPI.getUser(this, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject result) {
                    try {
                        if ((Boolean) result.get("success")) {
                            JSONObject data = result.getJSONObject("data");
                            name = data.getString("name");
                            gender = data.getInt("gender");
                            email = data.getString("email");
                            phone = data.getString("phone");
                            birthday = data.getString("birthday");

                            user_fname.setText(name);
                            if (gender == 1) {
                                user_male.setChecked(true);
                            } else user_female.setChecked(true);
                            user_email.setText(email);
                            user_phone.setText(phone);
                            user_birthday.setText(birthday);
                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override
                public void onError(JSONObject errorMessage) {

                }
            }, idUser);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
