package com.enginecore.app.recetascocina

import android.Manifest
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.Debug.getLocation
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.enginecore.app.recetascocina.databinding.MapaOrigenRecetasBinding
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.Task

class MapaOrigenActivity : AppCompatActivity(), OnMapReadyCallback, View.OnClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var currentLocation: Location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var binding: MapaOrigenRecetasBinding
    private var latitud: Double = 0.0
    private var longitud: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MapaOrigenRecetasBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.toolbar.tvTituloToolbar.text = getString(R.string.origen_de_receta)
        binding.toolbar.imvToolbar.setOnClickListener(this)
        binding.imgBtnBackHome.setOnClickListener(this)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        latitud = intent.getStringExtra("latitud")!!.toDouble()
        longitud = intent.getStringExtra("longitud")!!.toDouble()
        createFragment()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        placeMarketOnMap()
    }

    private fun createFragment() {
        val mapFragment: SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun placeMarketOnMap() {
        val cooredenadas = LatLng(latitud, longitud)
        val marker: MarkerOptions = MarkerOptions().position(cooredenadas).title("Origen de receta")
        mMap.addMarker(marker)
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cooredenadas, 4f),
        500,
        null)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.imv_toolbar -> {
                finish()
            }
            R.id.img_btn_back_home -> {
                startActivity(Intent(this, MainActivity::class.java))
                finishAffinity()
            }
        }
    }
}