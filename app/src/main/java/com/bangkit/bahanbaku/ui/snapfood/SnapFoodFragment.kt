package com.bangkit.bahanbaku.ui.snapfood

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bangkit.bahanbaku.R
import com.bangkit.bahanbaku.databinding.FragmentSnapFoodBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SnapFoodFragment : Fragment() {

    private val binding: FragmentSnapFoodBinding by lazy {
        FragmentSnapFoodBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_snap_food, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: SnapFoodViewModel by viewModels()

        setupView(viewModel)
    }

    private fun setupView(viewModel: SnapFoodViewModel) {
        binding.btnTakePhoto.setOnClickListener {

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }

    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }
}