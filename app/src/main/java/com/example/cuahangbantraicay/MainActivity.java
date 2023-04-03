package com.example.cuahangbantraicay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.cuahangbantraicay.Fragment.FragmentHomeProduct;
import com.example.cuahangbantraicay.Utils.internet;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    FragmentHomeProduct fragmentHomeProduct = null;

    private void setControl() {
        drawerLayout = findViewById(R.id.drawerlayout);
        viewFlipper = findViewById(R.id.viewflipper);
        navigationView = findViewById(R.id.navigationview);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }
    private void setEvent() {
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Banner();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()) {
                    case R.id.mn_DangNhap:
                        Toast.makeText(MainActivity.this, "Đăng nhập", Toast.LENGTH_SHORT).show();
                        if (fragmentHomeProduct==null)
                            fragmentHomeProduct= new FragmentHomeProduct();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.NoiDung,fragmentHomeProduct)
                                .commit();
                        break;
                    case R.id.mn_CaiDat:
                        Toast.makeText(MainActivity.this, "Cài đặt", Toast.LENGTH_SHORT).show();


                        break;
                }
                if (drawerLayout.isDrawerOpen(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);

                return false;
            }
        });
    }

    private void Banner() {
        ImageView view1 = new ImageView(getApplicationContext());
        view1.setImageResource(R.drawable.ic_launcher_background);
        ImageView view2 = new ImageView(getApplicationContext());
        view2.setImageResource(R.drawable.logo);
        viewFlipper.addView(view1);
        viewFlipper.addView(view2);
        viewFlipper.setAutoStart(true);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        internet.isConnected(this);
        setContentView(R.layout.activity_main);
        setControl();
        if (fragmentHomeProduct==null)
            fragmentHomeProduct= new FragmentHomeProduct();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.NoiDung,fragmentHomeProduct)
                .commit();

        setEvent();

    }
}