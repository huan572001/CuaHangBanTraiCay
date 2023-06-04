package com.example.cuahangbantraicay.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class authAPI {
    private SharedPreferences sharedPreferences;

    public authAPI(Context context) {
        sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
    }
    public  Map<String, String> getAuthorizationHeaders() {
        String token=sharedPreferences.getString("token","");

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + token);
        return headers;
    }
}
