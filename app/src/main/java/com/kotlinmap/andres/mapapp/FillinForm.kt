package com.kotlinmap.andres.mapapp

import android.content.Context
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_fillin_form.*
import kotlinx.android.synthetic.main.content_fillin_form.*
import org.jetbrains.anko.toast
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
            //Get location1
            val locationName = edLocationName.text.toString()
            val coordinates = getLocationFromAddress(this, locationName)

//            toast("andres " + "lat " + coordinates!!.latitude + ", long :" + coordinates!!.longitude)
//            if (locationName1 == "") {
//                toast("setLat(): " + locationName1)
//            }

            //Get location2
            val locationName2 = edLocationName2.text.toString()
            val coordinates2 = getLocationFromAddress(this, locationName2)

            //Get location2
            val locationName3 = edLocationName3.text.toString()
            val coordinates3 = getLocationFromAddress(this, locationName3)

            // Start map activity
            val i = Intent(this@FillinForm, MapsActivity::class.java)
            // i.putExtra("lat", 37.773972)
            i.putExtra("name", locationName)
            i.putExtra("lat", coordinates?.latitude.toString())
            i.putExtra("long", coordinates?.longitude.toString())

            //location 2
            i.putExtra("name2", locationName2)
            i.putExtra("lat2", coordinates2?.latitude.toString() )
            i.putExtra("long2", coordinates2?.longitude.toString() )

            //location 3
            i.putExtra("name3", locationName3)
            i.putExtra("lat3", coordinates3?.latitude.toString())
            i.putExtra("long3", coordinates3?.longitude.toString())

//            if (locationName == null|| locationName2 == null || locationName3 ==null) {
            if (!checkTextLength(edLocationName) || !checkTextLength(edLocationName2)|| !checkTextLength(edLocationName3))
                toast("Fields cannot be empty!")
            else
                startActivity(i)
        }

        // comment out FAB
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }


    }

    fun checkTextLength(editText: EditText): Boolean {
        var length = editText.length()
        if (length > 0)
            return true
        else
            return false
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
//            toast("location " + location.getLatitude() + location.getLongitude())

            resLatLng = LatLng(location.getLatitude(), location.getLongitude())

        } catch (ex: IOException) {

            ex.printStackTrace()
            toast("error")
        }

        return resLatLng
    }
}
