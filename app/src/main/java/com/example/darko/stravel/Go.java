package com.example.darko.stravel;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Go extends AppCompatActivity implements OnMapReadyCallback{

    GoogleMap mMap;
    SupportMapFragment fm;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go);
        fm= (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_map);
        fm.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng riva = new LatLng(43.508129, 16.438204);
        LatLng dioklecianPalace= new LatLng(43.508148, 16.439974);
        LatLng trainStation = new LatLng(43.504778, 16.443263);
        mMap.addMarker(new MarkerOptions().position(riva).title("Riva"));
        mMap.addMarker(new MarkerOptions().position(dioklecianPalace).title("Dioklecian palace"));
        mMap.addMarker(new MarkerOptions().position(trainStation).title("Train station Split"));
        mMap.moveCamera((CameraUpdateFactory.newLatLng(riva)));
        mMap.animateCamera( CameraUpdateFactory.zoomTo( 14.0f ) );
        mMap.setPadding(0,200,0,0);

    }

}
