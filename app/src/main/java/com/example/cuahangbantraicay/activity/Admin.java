package com.example.cuahangbantraicay.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.cuahangbantraicay.Fragment.AdminOrderFragment;
import com.example.cuahangbantraicay.Fragment.FirstFragment;
import com.example.cuahangbantraicay.Fragment.managerCategory;
//import com.example.cuahangbantraicay.Fragment.managerCreateProduct;
import com.example.cuahangbantraicay.Fragment.managerProduct;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.adapter.AdminProductAdapter;
import com.example.cuahangbantraicay.adapter.CategoryAdapter;
import com.example.cuahangbantraicay.adapter.FirstAdapter;
import com.google.android.material.navigation.NavigationView;

public class Admin extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    Toolbar toolbar;
    managerProduct managerproduct = null;


    //    managerCreateProduct managerCreateProduct = null;
    managerCategory managerCategory = null;
    AdminOrderFragment adminOrderFragment=null;
    AdminProductAdapter adminProductAdapter =null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().hide();
        if (ManagerProductDetail.isActive || ManagerProductCreate.isActive || AdminProductAdapter.isActive) {
            initFragment();
            ManagerProductDetail.isActive = false;
            ManagerProductCreate.isActive = false;
            AdminProductAdapter.isActive = false;

        }
        if (FirstAdapter.isActive || DetailOrderH.isActive){
            initFragmentOrder();
            FirstAdapter.isActive = false;
            DetailOrderH.isActive = false;

        }
        if (CategoryDetail.isActive || CategoryCreate.isActive || CategoryAdapter.isActive){
            initFragmentCategory();
            CategoryDetail.isActive = false;
            CategoryCreate.isActive= false;
            CategoryAdapter.isActive = false;
        }



        setContentView(R.layout.activity_admin);
        setControl();
        setEvent();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    private void initFragment() {
        managerproduct = new managerProduct();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.NoiDung, new managerProduct())
                .commit();
    }
    private void initFragmentCategory() {
        managerCategory = new managerCategory();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.NoiDung, new managerCategory())
                .commit();
    }
    private void initFragmentOrder() {
        adminOrderFragment = new AdminOrderFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.NoiDung, new AdminOrderFragment())
                .commit();
    }

    private void setEvent() {
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        banner();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.product:
                        Toast.makeText(Admin.this, "Product", Toast.LENGTH_SHORT).show();
                        if (managerproduct == null) {
                            managerproduct = new managerProduct();
                        }
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.NoiDung, new managerProduct())
                                .commit();
                        break;

                    case R.id.category:
                        Toast.makeText(Admin.this, "create", Toast.LENGTH_SHORT).show();
                        if (managerCategory == null) {
                            managerCategory = new managerCategory();
                        }
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.NoiDung, new managerCategory())
                                .commit();
                        break;
                    case R.id.revenue:
                        Toast.makeText(Admin.this, "Cart", Toast.LENGTH_SHORT).show();
                        replaceFragment(new AdminOrderFragment());
                        break;
                    case R.id.pdf:
                        Toast.makeText(Admin.this, "Thongke", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), ThongKe.class);
                        startActivity(intent);
                        break;


                    case R.id.logout:

                        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("token");
                        editor.remove("role");
                        editor.apply();
                        startActivity(new Intent(getApplicationContext(), DangNhap.class));
                        break;
                }
                if (drawerLayout.isDrawerOpen(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    private void banner() {

    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.NoiDung,fragment);
        transaction.commit();
    }

    private void setControl() {
        drawerLayout = findViewById(R.id.draw);
        navigationView = findViewById(R.id.navigate);
        viewFlipper = findViewById(R.id.viewflipper);
    }


}