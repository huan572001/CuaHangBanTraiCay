package com.example.cuahangbantraicay.Utils;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

public interface VolleyCallback1 {
    void onSuccess(JSONObject result) throws JSONException;

    void onError(VolleyError errorMessage);
}
