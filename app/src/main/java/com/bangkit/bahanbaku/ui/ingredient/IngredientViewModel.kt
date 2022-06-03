package com.bangkit.bahanbaku.ui.ingredient

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.bahanbaku.data.local.datastore.UserPreferences
import com.bangkit.bahanbaku.data.remote.response.IngredientResults
import com.bangkit.bahanbaku.data.repository.IngredientRepository
import com.bangkit.bahanbaku.data.repository.ProfileRepository
import com.bangkit.bahanbaku.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IngredientViewModel @Inject constructor(
    private val repository: IngredientRepository,
    private val profileRepository: ProfileRepository
) :
    ViewModel() {
    fun getIngredients(token: String, search: List<String>): LiveData<Result<IngredientResults>> {
        var searchStr = ""
        search.forEachIndexed { index, s ->
            searchStr += if (index == search.lastIndex) {
                s
            } else {
                "$s,"
            }
        }

        return repository.getIngredient(token, searchStr)
    }

    fun getToken() = profileRepository.getToken().asLiveData()
}