package com.bangkit.bahanbaku.ui.snapfood.landing

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bangkit.bahanbaku.R
import com.bangkit.bahanbaku.databinding.FragmentSnapFoodLandingBinding
import com.bangkit.bahanbaku.ui.camera.CameraActivity

class SnapFoodLandingFragment : Fragment() {

    companion object {
        fun newInstance() = SnapFoodLandingFragment()
    }

    private val binding: FragmentSnapFoodLandingBinding by lazy {
        FragmentSnapFoodLandingBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: SnapFoodLandingViewModel by viewModels()

        setupView(viewModel)
    }

    private fun setupView(viewModel: SnapFoodLandingViewModel) {
        binding.btnTakePhoto.setOnClickListener {
            val intent = Intent(requireContext(), CameraActivity::class.java)
            startActivity(intent)
        }
    }
}