package com.bangkit.bahanbaku.ui.bookmark

import androidx.lifecycle.ViewModel
import com.bangkit.bahanbaku.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(private val repository: ProfileRepository) : ViewModel() {
    fun getBookmarks(token: String) = repository.getBookmarks(token)

    fun deleteBookmarks(token: String, id: Int) = repository.deleteBookmark(token, id)
}