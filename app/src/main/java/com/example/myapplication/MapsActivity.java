package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
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

public class MapsActivity extends AppCompatActivity implements
        GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback {

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

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment =
        (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        }

@Override
public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
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
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
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

        }
