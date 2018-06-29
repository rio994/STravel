package com.example.darko.stravel;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
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
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
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
    private static final float DEFAULT_ZOOM_ON_ITEM_CLICKED = 14.5f;
    private static final LatLng DEFAULT_LAT_LNG = new LatLng(43.511032,16.436404);

    //variables
    private boolean mLocationPermissionGranted = false;
    private GoogleMap mMap;
    private SupportMapFragment fm;
    private Marker mMarker;
    private DatabaseSingleton databaseSingleton;
    private boolean isInfoWindowShown=false;
    private LocationManager locationManager;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private BitmapDescriptor markerBlue;
    private BitmapDescriptor markerRed;


    //navigation menu
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private EditText searchBar;
    private NavigationMenuView navMenuView;
    private View navDrawerHeader;

    private ArrayList<TableAtmToilet> atmToilets;
    private ArrayList<TableRestaurant> restaurants;
    private ArrayList<TableBarShopping> barShopping;
    private ArrayList<TableBeach> beaches;
    private ArrayList<TablePHP> php;
    private ArrayList<TableTransport> transport;

    //icons
    private ImageView gpsButton, navMenu;

    //todo database holds more info than needed

    //todo add bank and desserts to navigation drawer

    //adding to todo list - add a fragment for a user to select between services instead of pressing on buttons (services)


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go);
        locationManager=(LocationManager)getApplicationContext().getSystemService((Context.LOCATION_SERVICE));
        markerBlue = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.markerblue));
        markerRed = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.markerred));
        gpsButton = (ImageView)findViewById(R.id.ic_gps);
        searchBar = (EditText)findViewById(R.id.map_search_edit_text);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView)findViewById(R.id.nav_view);
        mNavigationView.bringToFront();
        navMenuView = (NavigationMenuView) mNavigationView.getChildAt(0);
        navMenuView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        navDrawerHeader = mNavigationView.getHeaderView(0);
        navMenu=(ImageView)findViewById(R.id.ic_menu);
        databaseSingleton=DatabaseSingleton.getInstance();
        atmToilets = databaseSingleton.getTableAtmToilets();
        restaurants = databaseSingleton.getTableRestaurants();
        barShopping = databaseSingleton.getTableBarShoppings();
        beaches=databaseSingleton.getTableBeaches();
        php=databaseSingleton.getTablePHPs();
        transport=databaseSingleton.getTableTransport();



        //explicitly check permission and init map
        getLocationPermission();

        //setting onClick listeners for searchBar, gspButton, navMenu
        init();

        //set map content from  navigation item selected
        setupNavigationContent();

    }



    //called once by API to setup map for first time (initial setup)
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //if gps provider is on
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            //if location permission is enabled get my current location
            if (mLocationPermissionGranted) {
                getCurrentDeviceLocation();
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                mMap.setMyLocationEnabled(true);
            } else {
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_LAT_LNG, DEFAULT_ZOOM_ON_ITEM_CLICKED));
                Toast.makeText(getApplicationContext(), "Location permission denied, current location unavailable", Toast.LENGTH_LONG).show();
            }
        }else{
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_LAT_LNG, DEFAULT_ZOOM_ON_ITEM_CLICKED));
            Toast.makeText(getApplicationContext(), "GPS provider disabled, current location unavailable", Toast.LENGTH_LONG).show();
        }
        //hide unnecessary default location button
        mMap.getUiSettings().setMyLocationButtonEnabled(false);

        //on Marker listener
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                //if marker doesn't have snippet, marker is set by searchBar
                if( marker.getSnippet()!=null) {
                    //enable switching between markers without click for close marker
                    if (mMarker.equals(marker)) {
                        if (isInfoWindowShown) {
                            mMarker.hideInfoWindow();
                            isInfoWindowShown = false;
                            return true;
                        } else {
                            marker.showInfoWindow();
                            isInfoWindowShown = true;
                            return false;
                        }

                    } else {
                        marker.showInfoWindow();
                        mMarker = marker;
                        isInfoWindowShown = true;
                        return false;
                    }
                }
                //todo add else statement and create snippet and title for marker from searchBar
                return true;
            }
        });


    }

    //setNavigationItemSelectedListener
    public void setupNavigationContent(){
        //onCLick close navigation Drawer
        navDrawerHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }
        });


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
                mMap.clear();
                for(int i = 0; i< restaurants.size(); i++){
                    String snippet = restaurants.get(i).getNumberOfReviews() + " reviews"+"\n"+
                            "Type: "+ restaurants.get(i).getType()+"\n"+
                            "Working time: "+ restaurants.get(i).getWorkingTime()+"\n"+
                            "Phone: "+ restaurants.get(i).getPhone()+"\n"+
                            "Address: "+ restaurants.get(i).getAddress()+"\n"+
                            "Description: "+ restaurants.get(i).getDescription();

                    LatLng latLng = new LatLng(Double.parseDouble(restaurants.get(i).getLat()),Double.parseDouble(restaurants.get(i).getLon()));
                    MarkerOptions options = new MarkerOptions().position(latLng).title(restaurants.get(i).getName()+ "\t  "+restaurants.get(i).getReview()+"\u2606").snippet(snippet);
                   if(Float.parseFloat(restaurants.get(i).getReview())<4.5)
                        options.icon(markerBlue);
                    else
                        options.icon(markerRed);
                   mMarker=mMap.addMarker(options);
                }
                mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(getApplicationContext()));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_LAT_LNG,DEFAULT_ZOOM_ON_ITEM_CLICKED));
                break;

            case R.id.bars_and_clubs:
                mMap.clear();
                for(int i = 0; i< barShopping.size(); i++){
                    if(barShopping.get(i).getSubtype().equals("bar")|| barShopping.get(i).getSubtype().equals("club")|| barShopping.get(i).getSubtype().equals("nb")|| barShopping.get(i).getSubtype().equals("cb")) {
                        String snippet = barShopping.get(i).getNumberOfReviews() + " reviews"+"\n"+
                                "Working time: "+ barShopping.get(i).getWorkingTime()+"\n"+
                                "Phone: "+ barShopping.get(i).getPhone()+"\n"+
                                "Address: "+ barShopping.get(i).getAddress()+"\n"+
                                "Description: "+ barShopping.get(i).getDescription();
                        LatLng latLng = new LatLng(Double.parseDouble(barShopping.get(i).getLat()),Double.parseDouble(barShopping.get(i).getLon()));
                        MarkerOptions options = new MarkerOptions().position(latLng).title(barShopping.get(i).getName()+ "\t  "+ barShopping.get(i).getReview()+"\u2606").snippet(snippet);
                        if(Float.parseFloat(barShopping.get(i).getReview())>4.5)
                            options.icon(markerBlue);
                        mMarker=mMap.addMarker(options);
                    }
                }
                mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(getApplicationContext()));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_LAT_LNG,DEFAULT_ZOOM_ON_ITEM_CLICKED));
                break;

            case R.id.cafes:
                mMap.clear();
                for(int i = 0; i< barShopping.size(); i++){
                    if(barShopping.get(i).getSubtype().equals("cafe")|| barShopping.get(i).getSubtype().equals("c")|| barShopping.get(i).getSubtype().equals("cb")) {
                        String snippet = barShopping.get(i).getNumberOfReviews() + " reviews"+"\n"+
                                "Working time: "+ barShopping.get(i).getWorkingTime()+"\n"+
                                "Phone: "+ barShopping.get(i).getPhone()+"\n"+
                                "Address: "+ barShopping.get(i).getAddress()+"\n"+
                                "Description: "+ barShopping.get(i).getDescription();
                        LatLng latLng = new LatLng(Double.parseDouble(barShopping.get(i).getLat()),Double.parseDouble(barShopping.get(i).getLon()));
                        MarkerOptions options = new MarkerOptions().position(latLng).title(barShopping.get(i).getName()+ "\t  "+ barShopping.get(i).getReview()+"\u2606").snippet(snippet);
                        if(Float.parseFloat(barShopping.get(i).getReview())>4.5)
                            options.icon(markerBlue);
                        mMarker=mMap.addMarker(options);
                    }
                }
                mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(getApplicationContext()));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_LAT_LNG,DEFAULT_ZOOM_ON_ITEM_CLICKED));
                break;

            case R.id.night_life:
                mMap.clear();
                for(int i = 0; i< barShopping.size(); i++){
                    if(barShopping.get(i).getSubtype().equals("night club")|| barShopping.get(i).getSubtype().equals("nb")) {
                        String snippet = barShopping.get(i).getNumberOfReviews() + " reviews"+"\n"+
                                "Working time: "+ barShopping.get(i).getWorkingTime()+"\n"+
                                "Phone: "+ barShopping.get(i).getPhone()+"\n"+
                                "Address: "+ barShopping.get(i).getAddress()+"\n"+
                                "Description: "+ barShopping.get(i).getDescription();
                        LatLng latLng = new LatLng(Double.parseDouble(barShopping.get(i).getLat()),Double.parseDouble(barShopping.get(i).getLon()));
                        MarkerOptions options = new MarkerOptions().position(latLng).title(barShopping.get(i).getName()+ "\t  "+ barShopping.get(i).getReview()+"\u2606").snippet(snippet);
                        if(Float.parseFloat(barShopping.get(i).getReview())>4.1)
                            options.icon(markerBlue);
                        mMarker=mMap.addMarker(options);
                    }
                }
                mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(getApplicationContext()));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_LAT_LNG,DEFAULT_ZOOM_ON_ITEM_CLICKED));
                break;

            case R.id.beach:
                mMap.clear();
                for(int i=0;i<beaches.size();i++){
                        String snippet = beaches.get(i).getNumberOfReviews() + " reviews"+"\n"+
                                "Address: "+ beaches.get(i).getAddress()+"\n"+
                                "Description: "+ beaches.get(i).getDescription();
                        LatLng latLng = new LatLng(Double.parseDouble(beaches.get(i).getLat()),Double.parseDouble(beaches.get(i).getLon()));
                        MarkerOptions options = new MarkerOptions().position(latLng).title(beaches.get(i).getName()+ "\t  "+beaches.get(i).getReview()+"\u2606").snippet(snippet);
                    if(Float.parseFloat(beaches.get(i).getReview())>4.5)
                        options.icon(markerBlue);
                        mMarker=mMap.addMarker(options);
                    }
                    mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(getApplicationContext()));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_LAT_LNG,DEFAULT_ZOOM_ON_ITEM_CLICKED));
                break;

            case R.id.atm:
                mMap.clear();
                for(int i = 0; i< atmToilets.size(); i++){
                    if(atmToilets.get(i).getSubtype().equals("atm")) {
                        String snippet = "Address: "+ atmToilets.get(i).getAddress()+"\n";
                        LatLng latLng = new LatLng(Double.parseDouble(atmToilets.get(i).getLat()),Double.parseDouble(atmToilets.get(i).getLon()));
                        MarkerOptions options = new MarkerOptions().position(latLng).title(atmToilets.get(i).getDescription()).snippet(snippet);
                        mMarker=mMap.addMarker(options);
                    }
                }
                mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(getApplicationContext()));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_LAT_LNG,DEFAULT_ZOOM_ON_ITEM_CLICKED));
                break;

            case R.id.public_toilet:
                mMap.clear();
                for(int i = 0; i< atmToilets.size(); i++){
                    if(atmToilets.get(i).getSubtype().equals("toilet")|| atmToilets.get(i).getSubtype().equals("t")) {
                        String snippet = "Address: "+ atmToilets.get(i).getAddress()+"\n";
                        LatLng latLng = new LatLng(Double.parseDouble(atmToilets.get(i).getLat()),Double.parseDouble(atmToilets.get(i).getLon()));
                        MarkerOptions options = new MarkerOptions().position(latLng).title(atmToilets.get(i).getDescription()).snippet(snippet);
                        mMarker=mMap.addMarker(options);
                    }
                }
                mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(getApplicationContext()));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_LAT_LNG,DEFAULT_ZOOM_ON_ITEM_CLICKED));
                break;

            case R.id.pharmacy:
                mMap.clear();
                for(int i=0;i<php.size();i++){
                    if(php.get(i).getSubtype().equals("pharmacy")|| php.get(i).getSubtype().equals("ph")) {
                        String snippet = "Working time: "+ php.get(i).getWorkingTime()+"\n"+
                                "Phone: "+ php.get(i).getPhone()+"\n"+
                                "Address: "+ php.get(i).getAddress()+"\n";
                        LatLng latLng = new LatLng(Double.parseDouble(php.get(i).getLat()),Double.parseDouble(php.get(i).getLon()));
                        MarkerOptions options = new MarkerOptions().position(latLng).title(php.get(i).getName()).snippet(snippet);
                        mMarker=mMap.addMarker(options);
                    }
                }
                mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(getApplicationContext()));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_LAT_LNG,DEFAULT_ZOOM_ON_ITEM_CLICKED));
                break;

            case R.id.hospitals:
                mMap.clear();
                for(int i=0;i<php.size();i++){
                    if(php.get(i).getSubtype().equals("hospital")|| php.get(i).getSubtype().equals("h")) {
                        String snippet = "Working time: "+ php.get(i).getWorkingTime()+"\n"+
                                "Phone: "+ php.get(i).getPhone()+"\n"+
                                "Address: "+ php.get(i).getAddress()+"\n";
                        LatLng latLng = new LatLng(Double.parseDouble(php.get(i).getLat()),Double.parseDouble(php.get(i).getLon()));
                        MarkerOptions options = new MarkerOptions().position(latLng).title(php.get(i).getName()).snippet(snippet);
                        mMarker=mMap.addMarker(options);
                    }
                }
                mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(getApplicationContext()));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_LAT_LNG,DEFAULT_ZOOM_ON_ITEM_CLICKED));
                break;

            case R.id.police_station:
                mMap.clear();
                for(int i=0;i<php.size();i++){
                    if(php.get(i).getSubtype().equals("police")|| php.get(i).getSubtype().equals("po")) {
                        String snippet = "Working time: "+ php.get(i).getWorkingTime()+"\n"+
                                "Phone: "+ php.get(i).getPhone()+"\n"+
                                "Address: "+ php.get(i).getAddress()+"\n";
                        LatLng latLng = new LatLng(Double.parseDouble(php.get(i).getLat()),Double.parseDouble(php.get(i).getLon()));
                        MarkerOptions options = new MarkerOptions().position(latLng).title(php.get(i).getName()).snippet(snippet);
                        mMarker=mMap.addMarker(options);
                    }
                }
                mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(getApplicationContext()));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_LAT_LNG,DEFAULT_ZOOM_ON_ITEM_CLICKED));
                break;

            case R.id.shopping_center:
                mMap.clear();
                for(int i = 0; i< barShopping.size(); i++){
                    if(barShopping.get(i).getSubtype().equals("center")|| barShopping.get(i).getSubtype().equals("sc")) {
                        String snippet = barShopping.get(i).getNumberOfReviews() + " reviews"+"\n"+
                                "Working time: "+ barShopping.get(i).getWorkingTime()+"\n"+
                                "Phone: "+ barShopping.get(i).getPhone()+"\n"+
                                "Address: "+ barShopping.get(i).getAddress()+"\n"+
                                "Description: "+ barShopping.get(i).getDescription();
                        LatLng latLng = new LatLng(Double.parseDouble(barShopping.get(i).getLat()),Double.parseDouble(barShopping.get(i).getLon()));
                        MarkerOptions options = new MarkerOptions().position(latLng).title(barShopping.get(i).getName()+ "\t  "+ barShopping.get(i).getReview()+"\u2606").snippet(snippet);
                        mMarker=mMap.addMarker(options);
                    }
                }
                mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(getApplicationContext()));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_LAT_LNG,DEFAULT_ZOOM_ON_ITEM_CLICKED));
                break;

            case R.id.airport:
                mMap.clear();
                for(int i=0;i<transport.size();i++){
                    if(transport.get(i).getSubtype().equals("airport")) {
                        String snippet = "Working time: "+ transport.get(i).getWorkingTime()+"\n"+
                                "Phone: "+ transport.get(i).getPhone()+"\n"+
                                "Address: "+ transport.get(i).getAddress()+"\n";
                        LatLng latLng = new LatLng(Double.parseDouble(transport.get(i).getLat()),Double.parseDouble(transport.get(i).getLon()));
                        MarkerOptions options = new MarkerOptions().position(latLng).title(transport.get(i).getName()).snippet(snippet);
                        mMarker=mMap.addMarker(options);
                    }
                }
                mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(getApplicationContext()));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(43.538415,16.378432),11));
                break;

            case R.id.bus_and_train:
                mMap.clear();
                for(int i=0;i<transport.size();i++){
                    if(transport.get(i).getSubtype().equals("bus")) {
                        String snippet = "Working time: "+ transport.get(i).getWorkingTime()+"\n"+
                                "Phone: "+ transport.get(i).getPhone()+"\n"+
                                "Address: "+ transport.get(i).getAddress()+"\n";
                        LatLng latLng = new LatLng(Double.parseDouble(transport.get(i).getLat()),Double.parseDouble(transport.get(i).getLon()));
                        MarkerOptions options = new MarkerOptions().position(latLng).title(transport.get(i).getName()).snippet(snippet);
                        mMarker=mMap.addMarker(options);
                    }
                }
                mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(getApplicationContext()));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_LAT_LNG,DEFAULT_ZOOM_ON_ITEM_CLICKED));
                break;

            case R.id.ferry_terminal:
                mMap.clear();
                for(int i=0;i<transport.size();i++){
                    if(transport.get(i).getSubtype().equals("ferry")) {
                        String snippet = "Working time: "+ transport.get(i).getWorkingTime()+"\n"+
                                "Phone: "+ transport.get(i).getPhone()+"\n"+
                                "Address: "+ transport.get(i).getAddress()+"\n";
                        LatLng latLng = new LatLng(Double.parseDouble(transport.get(i).getLat()),Double.parseDouble(transport.get(i).getLon()));
                        MarkerOptions options = new MarkerOptions().position(latLng).title(transport.get(i).getName()).snippet(snippet);
                        mMarker=mMap.addMarker(options);
                    }
                }
                mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(getApplicationContext()));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_LAT_LNG,DEFAULT_ZOOM_ON_ITEM_CLICKED));
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
        locationManager=(LocationManager)getApplicationContext().getSystemService((Context.LOCATION_SERVICE));
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
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
                                moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), DEFAULT_ZOOM, "My location");
                            } else {
                                Toast.makeText(getApplicationContext(), " Location  is null", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            } catch (SecurityException e) {
                Toast.makeText(getApplicationContext(), " Error while getting current location", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(getApplicationContext(), "GPS provider disabled, current location unavailable", Toast.LENGTH_LONG).show();
        }
    }

    //explicitly check permission
    private void getLocationPermission() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};
        if (ContextCompat.checkSelfPermission(getApplicationContext(),  COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted=true;
                initMap();
            }else {

                ActivityCompat.requestPermissions(Go.this, permissions,REQUEST_CODE);
            }
        }else {
            ActivityCompat.requestPermissions(Go.this, permissions,REQUEST_CODE);
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

        navMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);
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
                            Toast.makeText(getApplicationContext(),"Permissions denied! ",Toast.LENGTH_SHORT).show();
                            mLocationPermissionGranted = false;
                            initMap();
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
        Geocoder geocoder = new Geocoder(getApplicationContext());
        String searchBarInput = searchBar.getText().toString();
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

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_top);
    }


}


