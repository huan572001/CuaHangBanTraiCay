package com.example.cuahangbantraicay.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.cuahangbantraicay.Fragment.FragmentHomeProduct;
import com.example.cuahangbantraicay.Fragment.HomeFragment;
import com.example.cuahangbantraicay.Fragment.ProfileFragment;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.Utils.internet;
import com.example.cuahangbantraicay.helper.Converter;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_PROFILE = 1;
    private static final int FRAGMENT_OFFERS = 2;
    private static final int FRAGMENT_NEW_PRODUCT = 3;
    private static final int FRAGMENT_POPULAR_PRODUCTS = 4;
    private static final int FRAGMENT_CATEGORY = 5;
    private static final int FRAGMENT_SEARCH = 6;
    private static final int FRAGMENT_MY_ORDER = 7;
    private static final int FRAGMENT_MY_CART = 8;

    private static int cart_count = 0;
    private int mCurrentFragment = FRAGMENT_HOME;
    private DrawerLayout mdrawerLayout;

    @SuppressLint("ResourceAsColor")
    static void centerToolbarTitle(@NonNull final Toolbar toolbar) {
        final CharSequence title = toolbar.getTitle();
        final ArrayList<View> outViews = new ArrayList<>(1);
        toolbar.findViewsWithText(outViews, title, View.FIND_VIEWS_WITH_TEXT);
        if (!outViews.isEmpty()) {
            final TextView titleView = (TextView) outViews.get(0);
            titleView.setGravity(Gravity.CENTER);
            titleView.setTextColor(Color.parseColor("#FAD23C"));
            final Toolbar.LayoutParams layoutParams = (Toolbar.LayoutParams) titleView.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            toolbar.requestLayout();
            //also you can use titleView for changing font: titleView.setTypeface(Typeface);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        centerToolbarTitle(toolbar);

        mdrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,mdrawerLayout,toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mdrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new HomeFragment());
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()){
            case R.id.nav_home:
                if(mCurrentFragment != FRAGMENT_HOME) {
                    replaceFragment(new HomeFragment());
                    mCurrentFragment = FRAGMENT_HOME;
                }
                break;
            case R.id.nav_profile:
                if(mCurrentFragment != FRAGMENT_PROFILE){
                    replaceFragment(new ProfileFragment());
                    mCurrentFragment = FRAGMENT_PROFILE;
                }
                break;
            case R.id.nav_offers:
                if(mCurrentFragment != FRAGMENT_PROFILE){
                    replaceFragment(new ProfileFragment());
                    mCurrentFragment = FRAGMENT_PROFILE;
                }
                break;
            case R.id.nav_new_product:
                if(mCurrentFragment != FRAGMENT_PROFILE){
                    replaceFragment(new ProfileFragment());
                    mCurrentFragment = FRAGMENT_PROFILE;
                }
                break;
            case R.id.nav_popular_products:
                if(mCurrentFragment != FRAGMENT_PROFILE){
                    replaceFragment(new ProfileFragment());
                    mCurrentFragment = FRAGMENT_PROFILE;
                }
                break;
            case R.id.nav_category:
                if(mCurrentFragment != FRAGMENT_PROFILE){
                    replaceFragment(new ProfileFragment());
                    mCurrentFragment = FRAGMENT_PROFILE;
                }
                break;
            case R.id.nav_search:
                if(mCurrentFragment != FRAGMENT_PROFILE){
                    replaceFragment(new ProfileFragment());
                    mCurrentFragment = FRAGMENT_PROFILE;
                }
                break;
            case R.id.nav_my_order:
                if(mCurrentFragment != FRAGMENT_PROFILE){
                    replaceFragment(new ProfileFragment());
                    mCurrentFragment = FRAGMENT_PROFILE;
                }
                break;
            case R.id.nav_my_cart:
                if(mCurrentFragment != FRAGMENT_PROFILE){
                    replaceFragment(new ProfileFragment());
                    mCurrentFragment = FRAGMENT_PROFILE;
                }
                break;
            case R.id.logout:
                if(mCurrentFragment != FRAGMENT_PROFILE){
                    replaceFragment(new ProfileFragment());
                    mCurrentFragment = FRAGMENT_PROFILE;
                }
                break;
            }

        mdrawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }
    public void onBackPressed(){
        if(mdrawerLayout.isDrawerOpen(GravityCompat.START)){
            mdrawerLayout.closeDrawer(GravityCompat.START);
        }else super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_navigasion, menu);
        MenuItem menuItem = menu.findItem(R.id.cart_action);
        menuItem.setIcon(Converter.convertLayoutToImage(MainActivity.this, cart_count, R.drawable.ic_shopping_basket));

        return true;
    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.NoiDung,fragment);
        transaction.commit();
    }
}