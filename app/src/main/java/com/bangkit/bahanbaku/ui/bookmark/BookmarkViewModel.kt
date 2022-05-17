package com.bangkit.bahanbaku.ui.bookmark

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.bahanbaku.data.repository.ProfileRepository
import com.bangkit.bahanbaku.di.AppModule

class BookmarkViewModel(private val repository: ProfileRepository) : ViewModel() {
    fun getBookmark() = repository.getBookmarks()

    class BookmarkViewModelFactory private constructor(private val profileRepository: ProfileRepository) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(BookmarkViewModel::class.java)) {
                return BookmarkViewModel(profileRepository) as T
            }

            throw IllegalArgumentException("Unknown ViewModel class ${modelClass.name}")
        }

        companion object {
            @Volatile
            private var instance: BookmarkViewModelFactory? = null

            fun getInstance(context: Context): BookmarkViewModelFactory =
                instance ?: synchronized(this) {
                    instance ?: BookmarkViewModelFactory(AppModule.provideProfileRepository(context))
                }.also { instance = it }
        }
    }
}