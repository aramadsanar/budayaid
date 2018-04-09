package com.example.armada_nasar.budayaid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewBudayaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_budaya);
        TextView tv = (TextView) findViewById(R.id.text);
        Bundle x = getIntent().getExtras();
        tv.setText(x.getString("province"));
        String in = x.getString("province");
        //a very manual data store. I think this shall not be permanent solution!
        DataPool dp = new DataPool();
        ArrayList<Budaya> budayaLokal = (ArrayList<Budaya>) dp.dict.get(in);
        tv.setText(budayaLokal.get(0).getmNamaBudaya());


    }
}
