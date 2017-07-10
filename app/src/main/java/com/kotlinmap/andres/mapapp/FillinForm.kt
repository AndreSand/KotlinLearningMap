package com.kotlinmap.andres.mapapp

import android.content.Context
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.model.LatLng

import kotlinx.android.synthetic.main.activity_fillin_form.*
import kotlinx.android.synthetic.main.content_fillin_form.*
import org.jetbrains.anko.*
import java.io.IOException

class FillinForm : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fillin_form)
//        setSupportActionBar(toolbar)

//        if (!wifiManager.isWifiEnabled()) {
//            vibrator.vibrate(200)
//            toast("Wifi is disabled. Please turn on!")
//        }


        btGoToMap.setOnClickListener {
            val locationName1 = editText.text.toString()
            val x = getLocationFromAddress(this, locationName1)

            toast("andres " + "lat " + x!!.latitude + ", long :" + x!!.longitude)
//            if (locationName1 == "") {
//                toast("setLat(): " + locationName1)
//            }
            val i = Intent(this@FillinForm, MapsActivity::class.java)
//            i.putExtra("lat", 37.773972)
            i.putExtra("name", locationName1)
            i.putExtra("lat", x.latitude.toString())
            i.putExtra("long", x.longitude.toString())

            startActivity(i)
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    fun getLocationFromAddress(context: Context, inputtedAddress: String): LatLng? {

        val coder = Geocoder(context)
        val address: List<Address>?
        var resLatLng: LatLng? = null

        try {
            // May throw an IOException
            address = coder.getFromLocationName(inputtedAddress, 5)
            if (address == null) {
                return null
            }

            if (address.size == 0) {
                return null
            }

            val location = address[0]
            location.getLatitude()
            location.getLongitude()
            toast("location " + location.getLatitude() + location.getLongitude())

            resLatLng = LatLng(location.getLatitude(), location.getLongitude())

        } catch (ex: IOException) {

            ex.printStackTrace()
            toast("error")
        }

        return resLatLng
    }

}
