package com.bangkit.bahanbaku.ui.maps

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.bangkit.bahanbaku.R
import com.bangkit.bahanbaku.databinding.ActivityMapsBinding
import com.bangkit.bahanbaku.ui.login.LoginActivity
import com.bangkit.bahanbaku.utils.Result
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private val binding: ActivityMapsBinding by lazy {
        ActivityMapsBinding.inflate(layoutInflater)
    }

    private val viewModel: MapsViewModel by viewModels()

    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var location: Location? = null

    private lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        getToken()
    }

    private fun getToken() {
        viewModel.getToken().observe(this) {
            if (it == "null") {
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            } else {
                val token = "Bearer $it"
                this.token = token
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        getLastLocation()
    }

    private fun setupMap(mMap: GoogleMap) {
        val foodName = intent.getStringExtra(EXTRA_FOOD_NAME)
        if (foodName != null) {
            viewModel.getNearby(token, foodName).observe(this) { result ->
                when (result) {
                    is Result.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    is Result.Error -> {
                        binding.progressBar.isVisible = false
                        Toast.makeText(this, result.error, Toast.LENGTH_SHORT).show()
                    }
                    is Result.Success -> {
                        binding.progressBar.isVisible = false
                        val restos = result.data.results

                        restos.forEach { resto ->
                            val position = LatLng(resto.location.lat, resto.location.lng)
                            val rating = getString(R.string.nearby_rating).format(
                                resto.rating,
                                resto.userRatingsTotal
                            )

                            val markerOption = MarkerOptions()
                                .position(position)
                                .title(resto.restaurantName)
                                .snippet(rating)

                            mMap.addMarker(markerOption)
                        }

                        location?.let {
                            mMap.moveCamera(
                                CameraUpdateFactory.newLatLngZoom(
                                    LatLng(
                                        location!!.latitude,
                                        location!!.longitude
                                    ), 12f
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getLastLocation() {
        if (checkPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            && checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                this.location = location
                viewModel.updateLocation(token, location.latitude, location.longitude)
                    .observe(this) {
                        if (it is Result.Success) {
                            setupMap(mMap)
                        }
                    }
            }
        } else {
            requestPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false -> {
                getLastLocation()
            }
            permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false -> {
                getLastLocation()
            }
        }
    }

    private fun checkPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        const val EXTRA_FOOD_NAME = "extra_food_name"
    }
}