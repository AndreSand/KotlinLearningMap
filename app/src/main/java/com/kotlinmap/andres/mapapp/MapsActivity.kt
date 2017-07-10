package com.kotlinmap.andres.mapapp

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import android.content.Intent
import android.location.Address
import com.google.android.gms.maps.model.Marker
import org.jetbrains.anko.toast
import android.widget.Toast
import android.location.Geocoder
import java.io.IOException


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        val lat = intent.getStringExtra("lat")
        val long = intent.getStringExtra("long")

        val latD = lat.toDouble()
        val longD = long.toDouble()
//        val lat = intent.getDoubleExtra("lat",0.0)
        toast("getLat(): " + lat + " -- " + long)

//        TODO
//        not able to get double from previous activity...

        // Add a marker in SF and move the camera
//        val SF = LatLng(37.773972, -122.431297)
        val SF = LatLng(latD, longD)
        mMap.addMarker(MarkerOptions().position(SF).title("Work in SF"))

        //on maeker click opens new activity
        mMap.setOnMarkerClickListener(object : GoogleMap.OnMarkerClickListener {
            override fun onMarkerClick(marker: Marker): Boolean {
                val i = Intent(this@MapsActivity, FillinForm::class.java)
//                i.putExtra("name", "andres")
                startActivity(i)
                return false //true don't show  marker title
            }
        })
        mMap.moveCamera(CameraUpdateFactory.newLatLng(SF))
    }


}
