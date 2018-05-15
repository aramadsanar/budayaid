package com.example.armada_nasar.budayaid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

/**
 * Created by Quantum Higgs on 5/15/2018.
 */

public class RecycleActivity extends AppCompatActivity {

    private RecyclerView rvCategory;
    private ArrayList<Budaya> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budaya_entry);

        rvCategory = findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);

        list = new ArrayList<>();
        list.addAll(DataPool.getListData());

        showRecyclerCardView();
    }

    private void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    private void showRecyclerList(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        CardviewAdapter listBudayaAdapter = new CardviewAdapter(this);
        listBudayaAdapter.setListBudaya(list);
        rvCategory.setAdapter(listBudayaAdapter);
    }


    //nampil card
    private void showRecyclerCardView(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        CardviewAdapter cardViewBudayaAdapter = new CardviewAdapter(this);
        cardViewBudayaAdapter.setListBudaya(list);
        rvCategory.setAdapter(cardViewBudayaAdapter);
    }

}
