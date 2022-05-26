package com.bangkit.bahanbaku.ui.ingredient

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.bangkit.bahanbaku.data.local.datastore.UserPreferences
import com.bangkit.bahanbaku.data.remote.response.ResultsItem
import com.bangkit.bahanbaku.data.repository.IngredientRepository
import com.bangkit.bahanbaku.utils.Result
import javax.inject.Inject

class IngredientViewModel @Inject constructor(
    private val repository: IngredientRepository,
    private val userPreferences: UserPreferences
) :
    ViewModel() {
    fun getIngredients(token: String, search: List<String>): LiveData<Result<List<ResultsItem>>> {
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

    fun getToken() = userPreferences.getToken().asLiveData()
}