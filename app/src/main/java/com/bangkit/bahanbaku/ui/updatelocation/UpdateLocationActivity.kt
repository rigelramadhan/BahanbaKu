package com.bangkit.bahanbaku.ui.updatelocation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.bangkit.bahanbaku.R
import com.bangkit.bahanbaku.databinding.ActivityUpdateLocationBinding
import com.bangkit.bahanbaku.ui.login.LoginActivity
import com.bangkit.bahanbaku.ui.main.MainActivity
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
class UpdateLocationActivity : AppCompatActivity(), OnMapReadyCallback {

    private val binding: ActivityUpdateLocationBinding by lazy {
        ActivityUpdateLocationBinding.inflate(layoutInflater)
    }

    private val viewModel: UpdateLocationViewModel by viewModels()

    private var location: Location? = null
    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient

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
            if (it.length <= 5) {
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            } else {
                val token = "Bearer $it"
                setupView(token)
            }
        }
    }

    private fun setupView(token: String) {
        binding.btnUpdateLocation.setOnClickListener {
            if (location != null) {
                viewModel.updateLocation(token, location!!.latitude, location!!.longitude)
                    .observe(this) { result ->
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
                                Toast.makeText(
                                    this,
                                    getString(R.string.location_updated_success),
                                    Toast.LENGTH_SHORT
                                ).show()

                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                        }
                    }
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        getLastLocation()
    }

    private fun showCurrentLocation(location: Location) {
        val loc = LatLng(location.latitude, location.longitude)
        mMap.addMarker(
            MarkerOptions()
                .position(loc)
                .title(getString(R.string.youre_here))
        )

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 15f))
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
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

    private fun getLastLocation() {
        if (checkPermission(Manifest.permission.ACCESS_FINE_LOCATION) && checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                this.location = location
                showCurrentLocation(location)
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

    private fun checkPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

}