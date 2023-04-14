package com.example.cuahangbantraicay.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.cuahangbantraicay.Fragment.HomeFragment;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.adapter.ViewPagerAdapter;
import com.google.android.material.navigation.NavigationView;

import java.util.Timer;
import java.util.TimerTask;

public class TrangChu extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    Timer timer;
    int page_position = 0;
    private int dotscount;
    private ImageView[] dots;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        Intent myIntent;
        switch (item.getItemId()) {
            case R.id.nav_home:
                myIntent = new Intent(TrangChu.this, TrangChu.class);
                TrangChu.this.startActivity(myIntent);
                return true;
            case R.id.nav_profile:
                myIntent = new Intent(TrangChu.this, Welcome.class);
                TrangChu.this.startActivity(myIntent);
                return true;
            case R.id.nav_offers:
                // Làm bất cứ cái gì đó
                return true;
            case R.id.nav_new_product:
                // Làm bất cứ cái gì đó
                return true;
            case R.id.nav_popular_products:
                // Làm bất cứ cái gì đó
                return true;
            case R.id.nav_category:
                // Làm bất cứ cái gì đó
                return true;
            case R.id.nav_search:
                // Làm bất cứ cái gì đó
                return true;
            case R.id.nav_my_order:
                // Làm bất cứ cái gì đó
                return true;
            case R.id.nav_my_cart:
                // Làm bất cứ cái gì đó
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_home:
                fragment = new HomeFragment();
                break;
            case R.id.nav_profile:
              //  fragment = new ProfileFragment();
                break;
            case R.id.nav_category:
               // fragment = new CategoryFragment();
                break;
            case R.id.nav_popular_products:
               // fragment = new PopularProductFragment();
                break;
            case R.id.nav_new_product:
               // fragment = new NewProductFragment();
                break;

            case R.id.nav_offers:
                //fragment = new OffrersFragment();
                break;
            case R.id.nav_search:
                //fragment = new CategoryFragment();
                break;
            case R.id.nav_my_order:
               // fragment = new MyOrderFragment();
                break;
            case R.id.nav_my_cart:
                startActivity(new Intent(getApplicationContext(), Welcome.class));
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                break;


        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.slide_from_right, R.anim.slide_to_left);
            ft.replace(R.id.content_frame, fragment);
            ft.commit();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        drawerLayout = findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        timer = new Timer();
        viewPager = findViewById(R.id.viewPager);

        sliderDotspanel = findViewById(R.id.SliderDots);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

        viewPager.setAdapter(viewPagerAdapter);

        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        scheduleSlider();
    }

    public void scheduleSlider() {

        final Handler handler = new Handler();

        final Runnable update = new Runnable() {
            public void run() {
                if (page_position == dotscount) {
                    page_position = 0;
                } else {
                    page_position = page_position + 1;
                }
                viewPager.setCurrentItem(page_position, true);
            }
        };

        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(update);
            }
        }, 500, 4000);
    }

    @Override
    protected void onStop() {
        timer.cancel();
        super.onStop();
    }

    @Override
    protected void onPause() {
        timer.cancel();
        super.onPause();
    }

}