package com.example.darko.stravel;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Go extends AppCompatActivity implements OnMapReadyCallback {

    //constants
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int REQUEST_CODE = 1950;
    private static final float DEFAULT_ZOOM = 15f;

    //variables
    //explicit check if location permissions are granted
    private boolean mLocationPermissionGranted = false;
    private GoogleMap mMap;
    private SupportMapFragment fm;
    //navigation menu
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private EditText searchBar;
    //icons
    private ImageView gpsButton;

    //adding to todo list - add a fragment for a user to select between services instead of pressing on buttons (services)


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go);
        gpsButton = (ImageView)findViewById(R.id.ic_gps);
        searchBar = (EditText)findViewById(R.id.map_search_edit_text);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView)findViewById(R.id.nav_view);
        mNavigationView.bringToFront();

        //explicitly check permission and init map
        getLocationPermission();

        //setting onClick listeners for searchBar, gspButton
        init();

        //set map content from  navigation item selected
        setupNavigationContent();



    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        //if GPS is enabled get my current location
        if (mLocationPermissionGranted) {
            getCurrentDeviceLocation();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            //show blue dot for current location on map
            mMap.setMyLocationEnabled(true);
            //hide unnecessary default location button
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
        }

    }

    //setNavigationItemSelectedListener
    public void setupNavigationContent(){
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                setMarkersOnSelectedNavigationItem(item);

                return false;
            }
        });
    }

    public void setMarkersOnSelectedNavigationItem(MenuItem item){

        switch (item.getItemId()){
            case R.id.restaurant:
                Toast.makeText(Go.this,"yolo",Toast.LENGTH_SHORT).show();
              //  item.setChecked(true);
                break;
            case R.id.bars_and_clubs:
                item.setChecked(true);
                Toast.makeText(Go.this,"yolo",Toast.LENGTH_SHORT).show();
                break;
            case R.id.cafes:
                item.setChecked(true);
                Toast.makeText(Go.this,"yolo",Toast.LENGTH_SHORT).show();
                break;
            case R.id.night_life:
                item.setChecked(true);
                break;
            case R.id.beach:
                item.setChecked(true);
                break;
            case R.id.atm:
                item.setChecked(true);
                break;
            case R.id.public_toilet:
                item.setChecked(true);
                break;
            case R.id.pharmacy:
                item.setChecked(true);
                break;
            case R.id.hospitals:
                item.setChecked(true);
                break;
            case R.id.police_station:
                item.setChecked(true);
                break;
            case R.id.shopping_center:
                item.setChecked(true);
                break;
            case R.id.airport:
                item.setChecked(true);
                break;
            case R.id.bus_and_train:
                item.setChecked(true);
                break;
            case R.id.ferry_terminal:
                item.setChecked(true);
                break;
            default:
                break;
            }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        }

    //close drawer on Back pressed or close activity
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //get current location
    private void getCurrentDeviceLocation() {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try {
            if (mLocationPermissionGranted) {
                Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            //if we get some location
                            Location currentLocation = (Location) task.getResult();
                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), DEFAULT_ZOOM,"My location");
                        } else {
                            Toast.makeText(getApplicationContext(), " Location  is null", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Toast.makeText(getApplicationContext(), " Error while getting current location", Toast.LENGTH_LONG).show();
        }
    }

    //explicitly check permission
    private void getLocationPermission() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};
        if (ContextCompat.checkSelfPermission(getApplicationContext(), FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(getApplicationContext(),  COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true;
                initMap();
            }else {

                ActivityCompat.requestPermissions(this, permissions,REQUEST_CODE);
            }
        }else {
            ActivityCompat.requestPermissions(this, permissions,REQUEST_CODE);
        }
    }

    //pick data from search bar and set actions for accepting search input, gpsButton onClickListener
    private void init(){
        searchBar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                //react on enter search or done //todo gets double search call if all keyEvents are enabled
                if(actionId == EditorInfo.IME_ACTION_SEARCH/*
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || keyEvent.getAction() == KeyEvent.ACTION_DOWN
                        || keyEvent.getAction() == KeyEvent.KEYCODE_ENTER*/){


                    //execute method for searching address
                    geoLocate();

                }
                return false;
            }
        });

        gpsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCurrentDeviceLocation();
            }
        });

        hideSoftKeyboard();
    }

    //initialize map
    private void initMap(){
        fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_map);
        fm.getMapAsync(this);
    }

    //compare requested permissions with requestPermissionResult
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode){
            case REQUEST_CODE:{
                //if grantResult > 0, we have some permissions
                if (grantResults.length>0){
                    for(int i = 0; i < grantResults.length; i++)
                    {
                        //check if all permissions are granted
                        if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
                            mLocationPermissionGranted = false;
                            return;
                        }
                    }
                    mLocationPermissionGranted = true;
                    //initialize map
                    initMap();

                }
            }
        }

    }

    //get location from address
    private void geoLocate(){
        String searchBarInput = searchBar.getText().toString();
        Geocoder geocoder = new Geocoder(getApplicationContext());
        List<Address> list = new ArrayList<>();

        try {
            //return list of address with with size of maxResult
            list = geocoder.getFromLocationName(searchBarInput,1);
        }catch (IOException e){

        }

        if(list.size() > 0){
            Address address = list.get(0);
            //check if searchBar input is in croatia
            if(address.getCountryName().equals("Hrvatska")) {
                searchBar.getText().clear();
                moveCamera(new LatLng(address.getLatitude(), address.getLongitude()), DEFAULT_ZOOM, address.getAddressLine(0));
                return;
            }else{
                searchBar.getText().clear();
                Toast.makeText(getApplicationContext(), "Searching is allowed only for Croatia \n\t\t\t\t\t\t\t\t\tTry with different input", Toast.LENGTH_SHORT).show();
                hideSoftKeyboard();
            }
        }else{
            Toast.makeText(getApplicationContext(), "No suitable address, check your input", Toast.LENGTH_SHORT).show();
            hideSoftKeyboard();
        }

    }

    //hide keyboard
    private void hideSoftKeyboard(){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(searchBar.getWindowToken(),0);
    }

    //move camera to focus myLocation or to set marker from searchBar input
    private void moveCamera(LatLng latLng,float zoom, String title) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom));

        if(!title.equals("My location")){
            MarkerOptions options = new MarkerOptions().position(latLng).title(title);
            mMap.addMarker(options);
        }

        hideSoftKeyboard();
    }
}
