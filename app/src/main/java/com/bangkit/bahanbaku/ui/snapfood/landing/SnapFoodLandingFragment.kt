package com.bangkit.bahanbaku.ui.snapfood.landing

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bangkit.bahanbaku.R

class SnapFoodLandingFragment : Fragment() {

    companion object {
        fun newInstance() = SnapFoodLandingFragment()
    }

    private lateinit var viewModel: SnapFoodLandingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_snap_food_landing, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SnapFoodLandingViewModel::class.java)
        // TODO: Use the ViewModel
    }

}