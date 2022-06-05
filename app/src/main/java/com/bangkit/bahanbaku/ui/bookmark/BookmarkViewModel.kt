package com.bangkit.bahanbaku.ui.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.bahanbaku.data.local.datastore.UserPreferences
import com.bangkit.bahanbaku.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val repository: ProfileRepository,
    private val profileRepository: ProfileRepository
) : ViewModel() {
    fun getBookmarks(token: String) = repository.getBookmarks(token)

    fun getToken() = profileRepository.getToken()

    fun deleteBookmarks(token: String, id: Int) = repository.deleteBookmarkByPosition(token, id)
}