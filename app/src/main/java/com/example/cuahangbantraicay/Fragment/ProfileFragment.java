package com.example.cuahangbantraicay.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cuahangbantraicay.API.UserAPI;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.Utils.VolleyCallback;
import com.example.cuahangbantraicay.activity.EditProfile;
import com.example.cuahangbantraicay.activity.Profile;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileFragment extends Fragment {

    int idUser;
    Button edit;
    TextView tv_fname, tv_phone, tv_gender, tv_birthday, tv_email;
    String name, email ,phone, birthday;
    Integer gender;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
         return inflater.inflate(R.layout.fragment_profile,container,false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setControl(view);
        setEvent();
        getUser();
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Profile");
    }

    public void setControl(View view){
        edit = view.findViewById(R.id.edit_profile);
        tv_fname = view.findViewById(R.id.tv_fname);
        tv_birthday = view.findViewById(R.id.tv_birthday);
        tv_phone = view.findViewById(R.id.tv_phone);
        tv_gender = view.findViewById(R.id.tv_gender);
        tv_email = view.findViewById(R.id.tv_email);
    }
    public void setEvent(){
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), EditProfile.class);
                startActivity(intent);
            }
        });
    }
    private void getUser(){
        try {
            UserAPI.getUser(getContext(), new VolleyCallback() {
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
