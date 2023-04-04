package com.example.cuahangbantraicay.Utils;

import org.json.JSONObject;

public interface VolleyCallback {
    void onSuccess(JSONObject result);

    void onError(JSONObject errorMessage);
}
