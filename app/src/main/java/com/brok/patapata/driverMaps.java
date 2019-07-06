package com.brok.patapata;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class driverMaps extends FragmentActivity implements OnMapReadyCallback {
    private DatabaseReference mReq;
    private GoogleMap mMap;
    private String userid;
    private String driverid;
    private Double dlat;
    private Double ulat;
    private Double dlng;
    private Double ulng;
    MarkerOptions driverlocation, userlocation;
    Polyline currentPolyline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_maps);
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
         userid = getIncomingIntent();
         driverid = FirebaseAuth.getInstance().getCurrentUser().getUid();
         //Delete all other requests the driver currently has.
  FirebaseDatabase.getInstance().getReference().child("requests").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String driver_id = snapshot.child("driverid").getValue(String.class);
                    if(driver_id==driverid) {

                            snapshot.getRef().removeValue();
                        }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//retrive user's location
       FirebaseDatabase.getInstance().getReference().child("users").child(userid).addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

               ulat =dataSnapshot.child("latitude").getValue(Double.class);
               ulng =dataSnapshot.child("longitude").getValue(Double.class);

           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });

//retrieve driver's location
        FirebaseDatabase.getInstance().getReference().child("driverdetails").child(driverid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                dlat =dataSnapshot.child("latitude").getValue(Double.class);
                dlng =dataSnapshot.child("longitude").getValue(Double.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        mMap = googleMap;

        // Add a marker on Driver Location and move the camera
        LatLng dlocation = new LatLng(dlat, dlng);
        LatLng ulocation = new LatLng(ulat, ulng);
      //  mMap.addMarker(new MarkerOptions().position(dlocation).title("You"));
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(dlocation));

        driverlocation = new MarkerOptions().position(dlocation).title("You");
       userlocation = new MarkerOptions().position(ulocation).title("Customer");

        //connecting drver and user via a path
        String url = getUrl(driverlocation.getPosition(), userlocation.getPosition(), "driving");
        
    }

//get user id from intent extra
    private String getIncomingIntent(){
        if(getIntent().hasExtra("User ID")){
         String userid = getIntent().getStringExtra("User ID");
         return userid;
        }

        return null;
    }
    private String getUrl(LatLng origin, LatLng dest, String directionMode){
        //origin
        String str_origin = "origin=" + origin.latitude+ "." + origin.longitude;
        //destination
        String str_dest = "destination=" + dest.latitude+ "." + dest.longitude;
        //mode
        String mode= "mode=" + directionMode;
        //Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        //output format
        String output = "json";
        //Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key);
        return url;
    }
}
