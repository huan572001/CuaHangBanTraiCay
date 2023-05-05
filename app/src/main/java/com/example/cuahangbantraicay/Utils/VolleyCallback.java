package com.example.cuahangbantraicay.Utils;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

public interface VolleyCallback {
    void onSuccess(JSONObject result);

    void onError(JSONObject errorMessage);
}
