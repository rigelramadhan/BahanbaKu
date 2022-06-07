package com.bangkit.bahanbaku.ui.snapfood

import android.Manifest
import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bangkit.bahanbaku.R
import com.bangkit.bahanbaku.databinding.FragmentSnapFoodBinding
import com.bangkit.bahanbaku.ui.camera.CameraActivity
import com.bangkit.bahanbaku.ui.snapfood.result.SnapFoodResultActivity
import com.bangkit.bahanbaku.utils.fromUriToFile
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

@AndroidEntryPoint
class SnapFoodFragment : Fragment() {

    private val binding: FragmentSnapFoodBinding by lazy {
        FragmentSnapFoodBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: SnapFoodViewModel by viewModels()

        setupView(viewModel)
    }

    private fun setupView(viewModel: SnapFoodViewModel) {
        binding.btnTakePhoto.setOnClickListener {
            Toast.makeText(requireContext(), "Button Pressed", Toast.LENGTH_SHORT).show()
            if (!allPermissionsGranted()) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    REQUIRED_PERMISSIONS,
                    REQUEST_CODE_PERMISSIONS
                )
            } else {
                val intent = Intent(requireContext(), CameraActivity::class.java)
                startActivity(intent)
            }
        }

        binding.btnGallery.setOnClickListener {
            startGallery()
        }
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, getString(R.string.import_picture))
        launcherIntentGallery.launch(chooser)
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            val selectedImage: Uri = it.data?.data as Uri
            val myFile = fromUriToFile(selectedImage, requireContext())
            val intent = Intent(requireContext(), SnapFoodResultActivity::class.java)
            intent.putExtra(SnapFoodResultActivity.EXTRA_PICTURE, myFile)
            startActivity(intent)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.permission_not_granted),
                    Toast.LENGTH_SHORT
                ).show()
                requireActivity().finish()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            requireActivity().baseContext,
            it
        ) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }
}