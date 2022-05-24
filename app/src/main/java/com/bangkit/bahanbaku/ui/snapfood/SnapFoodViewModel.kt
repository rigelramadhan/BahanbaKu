package com.bangkit.bahanbaku.ui.snapfood

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.bahanbaku.data.repository.FoodRepository
import com.bangkit.bahanbaku.di.DatabaseModule
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SnapFoodViewModel @Inject constructor(private val foodRepository: FoodRepository) : ViewModel() {

}