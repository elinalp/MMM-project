package com.istic.mmm.project;

import android.*;
import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMinZoomPreference(12.1f);
        mMap.setMaxZoomPreference(16.0f);
        // Add a marker in Sydney, Australia, and move the camera.
        LatLng rennes = new LatLng(48.1173421, -1.7075198);
        LatLng carrefour1 = new LatLng(48.10990899999999, -1.6565067999999883);
        LatLng carrefour3 = new LatLng(48.0830207, -1.6810617999999522);
        LatLng carrefour4 = new LatLng(48.1147851, -1.6768945999999687);
        LatLng carrefour5 = new LatLng(48.1103767, -1.6705676000000267);
        mMap.addMarker(new MarkerOptions().position(carrefour1).title("Carrefour 1"));
        mMap.addMarker(new MarkerOptions().position(carrefour3).title("Carrefour 3"));
        mMap.addMarker(new MarkerOptions().position(carrefour4).title("Carrefour 4"));
        mMap.addMarker(new MarkerOptions().position(carrefour5).title("Carrefour 5"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(rennes));
    }
}
