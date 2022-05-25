package com.bangkit.bahanbaku.ui.snapfood.result

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bangkit.bahanbaku.R

class SnapFoodResultFragment : Fragment() {

    companion object {
        fun newInstance() = SnapFoodResultFragment()
    }

    private lateinit var viewModel: SnapFoodResultViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_snap_food_result, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SnapFoodResultViewModel::class.java)
        // TODO: Use the ViewModel
    }

}