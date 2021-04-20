package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.jsoup.Jsoup;
import org.w3c.dom.Document;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends AppCompatActivity implements
        GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback {

        AppLocationService appLocationService;

        TextView tvAddress;

/**
 * Request code for location permission request.
 *
 * @see #onRequestPermissionsResult(int, String[], int[])
 */
private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

/**
 * Flag indicating whether a requested permission has been denied after returning in
 * {@link #onRequestPermissionsResult(int, String[], int[])}.
 */
private boolean permissionDenied = false;

private GoogleMap map;

        View mapView;

        SearchView searchView;

        static final String TAG = "MapHelper";

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);



        //tvAddress = (TextView) findViewById(R.id.tvAddress);



        SupportMapFragment mapFragment =
        (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapView = mapFragment.getView();
        mapFragment.getMapAsync(this);

        }

@Override
public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        //map.setPadding(0,100,0,0);
        new Handler().post(new Runnable() {
                @Override
                public void run() {
                        map.setPadding(0,150,0,0);
                }
        });

        searchView = findViewById(R.id.sv_location);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                        String location = searchView.getQuery().toString();
                        List<Address> addressList = null;
                        if (location !=null || !location.equals("") || map != null){
                                Geocoder geocoder = new Geocoder(MapsActivity.this);
                                try {
                                        addressList = geocoder.getFromLocationName(location, 1);
                                } catch (IOException e){
                                        e.printStackTrace();
                                }
                                if (addressList == null){
                                        Log.d(TAG,"Latitude and longitude not found");
                                }else {
                                        Address address = addressList.get(0);
                                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                                        map.addMarker(new MarkerOptions().position(latLng).title(location));
                                        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                                }
                        }
                        return false;
                }


                @Override
                public boolean onQueryTextChange(String newText) {
                        return false;
                }
        });

        map.setOnMyLocationButtonClickListener(this);
        map.setOnMyLocationClickListener(this);
        enableMyLocation();
        }

/**
 * Enables the My Location layer if the fine location permission has been granted.
 */
private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        == PackageManager.PERMISSION_GRANTED) {
        if (map != null) {
        map.setMyLocationEnabled(true);
        map.getUiSettings().setMyLocationButtonEnabled(true);
        }
        } else {
        // Permission to access the location is missing. Show rationale and request permission
        PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
        Manifest.permission.ACCESS_FINE_LOCATION, true);
        }
        }

@Override
public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
        }

@Override
public void onMyLocationClick(@NonNull Location location) {
        String StreetName = null;
        Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
        List<Address> addresses;
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        int maxResults = 1;
        try {
                addresses = gcd.getFromLocation(location.getLatitude(), location
                        .getLongitude(), 1);
                if (addresses.size() > 0)
                        StreetName = addresses.get(0).getThoroughfare();
                String s = /*longitude + "\n" + latitude +
                        "\n\n*/"My Currrent Street is: " + StreetName;
                Intent goToHouse = new Intent(getApplicationContext(), Housesbeta.class);
                startActivity(goToHouse);
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                //Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
        } catch (Exception e) {

        }
}

@Override
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
        return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults, Manifest.permission.ACCESS_FINE_LOCATION)) {
        // Enable the my location layer if the permission has been granted.
        enableMyLocation();
        } else {
        // Permission was denied. Display an error message
        // Display the missing permission error dialog when the fragments resume.
        permissionDenied = true;
        }
        }

@Override
protected void onResumeFragments() {
        super.onResumeFragments();
        if (permissionDenied) {
        // Permission was not granted, display error dialog.
        showMissingPermissionError();
        permissionDenied = false;
        }
        }

/**
 * Displays a dialog with error message explaining that the location permission is missing.
 */
private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog
        .newInstance(true).show(getSupportFragmentManager(), "dialog");
        }


        public Address getAddressForLocation(Context context, Location location) throws IOException {

                if (location == null) {
                        return null;
                }
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                int maxResults = 1;

                Geocoder gc = new Geocoder(context, Locale.getDefault());
                List<Address> addresses = gc.getFromLocation(latitude, longitude, maxResults);

                if (addresses.size() == 1) {
                        return addresses.get(0);
                } else {
                        return null;
                }
        }
        public void showSettingsAlert() {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                        MapsActivity.this);
                alertDialog.setTitle("SETTINGS");
                alertDialog.setMessage("Enable Location Provider! Go to settings menu?");
                alertDialog.setPositiveButton("Settings",
                        new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(
                                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                        MapsActivity.this.startActivity(intent);
                                }
                        });
                alertDialog.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                }
                        });
                alertDialog.show();
        }
        private class GeocoderHandler extends Handler {
                @Override
                public void handleMessage(Message message) {
                        String locationAddress;
                        switch (message.what) {
                                case 1:
                                        Bundle bundle = message.getData();
                                        locationAddress = bundle.getString("address");
                                        break;
                                default:
                                        locationAddress = null;
                        }
                        tvAddress.setText(locationAddress);
                }
        }

}


