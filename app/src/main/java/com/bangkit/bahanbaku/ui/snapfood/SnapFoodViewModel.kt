package com.bangkit.bahanbaku.ui.snapfood

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.bahanbaku.data.repository.FoodRepository
import com.bangkit.bahanbaku.di.AppModule

class SnapFoodViewModel(private val foodRepository: FoodRepository) : ViewModel() {

    class SnapFoodViewModelFactory(private val foodRepository: FoodRepository) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SnapFoodViewModel::class.java)) {
                return SnapFoodViewModel(foodRepository) as T
            }

            throw IllegalArgumentException("Unknown ViewModel class ${modelClass.name}")
        }

        companion object {
            @Volatile
            private var instance: SnapFoodViewModelFactory? = null

            fun getInstance(context: Context): SnapFoodViewModelFactory =
                instance ?: synchronized(this) {
                    instance ?: SnapFoodViewModelFactory(
                        AppModule.provideFoodRepository(
                            context
                        )
                    )
                }.also { instance = it }
        }
    }
}