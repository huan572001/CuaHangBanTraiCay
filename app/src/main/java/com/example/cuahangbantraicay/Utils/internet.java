package com.example.cuahangbantraicay.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class internet {
    public static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifi != null && wifi.isConnected()) || (mobile != null && mobile.isConnected())){
            Toast.makeText(context, "Kết Nối Internet Thành công", Toast.LENGTH_LONG).show();
            System.out.println("===========================ketnoi==============");
            return true;
        }
        Toast.makeText(context, "không có kết nối internet !!!", Toast.LENGTH_LONG).show();
        System.out.println("===========================ko ket noi==============");

        return false;
    }
}