package com.example.armada_nasar.budayaid;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class ViewBudayaActivity extends AppCompatActivity {
    private ArrayList<Budaya> alb = new ArrayList<>();
    int mProvinceId;
    String mProvinceFriendlyName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_budaya);

        Intent i = getIntent();
        Bundle params = i.getExtras();

        mProvinceId = params.getInt("provinceId");
        mProvinceFriendlyName = params.getString("provinceFriendlyName");

        ViewPager budayaViewPager = findViewById(R.id.budayaViewPager);
        TabLayout budayaTab = findViewById(R.id.budayaTab);

        budayaViewPager.setAdapter(new BudayaByCategoryFragmentPagerAdapter(this, getSupportFragmentManager(), mProvinceId));
        budayaTab.setupWithViewPager(budayaViewPager);

        setTitle(mProvinceFriendlyName);
    }
}
