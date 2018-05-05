package com.example.armada_nasar.budayaid;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // SupportMapFragment smf = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        //smf.getMapAsync(this);
        String imageUri = "http://35.198.228.52:6014/getImage/5f7.jpg/";
        ImageView ivBasicImage = (ImageView) findViewById(R.id.tes);
        Picasso.with(getApplicationContext()).load(imageUri).into(ivBasicImage);
    }

    /*@Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setOnMarkerClickListener(this);
        LatLng tes = new LatLng(-1, 1);
        googleMap.addMarker(new MarkerOptions().position(tes).title("tes"));

        tes = new LatLng(2, 2);
        googleMap.addMarker(new MarkerOptions().position(tes).title("tes 2"));

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(getApplicationContext(), "NOT IMPLEMENTED YET!", Toast.LENGTH_LONG).show();
        Intent i  = new Intent(MainActivity.this, ViewBudayaActivity.class);
        i.putExtra("province", marker.getTitle());
        startActivity(i);

        return true;
    }*/
}
