package com.example.dailynews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dailynews.Adapter.FragmentAdapter;
import com.example.dailynews.Adapter.MainListAdapter;
import com.example.dailynews.Model.Article;
import com.example.dailynews.Model.MainData;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    public static LinearLayout nointernet;
    ImageButton retry;
    TabLayout tabLayout;
    ViewPager viewPager;
//    Spinner spinner;
//    public static String a;
//    String[] country = new String[]{"Argentina","Australia","Austria","Belgium","Brazil", "Bulgaria", "Canada", "China", "Colombia", "Cuba", "Czech Republic", "Egypt", "France", "Germany", "Greece", "Hong Kong", "Hungary", "India", "Indonesia", "Ireland", "Israel", "Italy", "Japan", "Latvia", "Lithuania", "Malaysia", "Mexico", "Morocco", "Netherlands", "New Zealand", "Nigeria", "Norway", "Philippines", "Poland", "Portugal", "Romania", "Russia", "Saudi Arabia", "Serbia", "Singapore", "Slovakia", "Slovenia", "South Africa", "South Korea", "Sweden", "Switzerland", "Taiwan", "Thailand", "Turkey", "UAE", "Ukraine", "United Kingdom", "United States", "Venuzuela"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nointernet = findViewById(R.id.nointernet);
        retry = findViewById(R.id.retry);
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);
//        spinner = findViewById(R.id.spinner);
//        getSupportActionBar().hide();

        tabLayout.addTab(tabLayout.newTab().setText("All"));
        tabLayout.addTab(tabLayout.newTab().setText("Business"));
        tabLayout.addTab(tabLayout.newTab().setText("Entertainment"));
        tabLayout.addTab(tabLayout.newTab().setText("Health"));
        tabLayout.addTab(tabLayout.newTab().setText("Sports"));
        tabLayout.addTab(tabLayout.newTab().setText("Technology"));


        FragmentPagerAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChackConnection();
            }
        });
    }

    private void ChackConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo network = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (wifi.isConnected()) {
            nointernet.setVisibility(View.GONE);

        } else if (network.isConnected()) {
            nointernet.setVisibility(View.GONE);
        } else {
            nointernet.setVisibility(View.VISIBLE);
        }
    }


}