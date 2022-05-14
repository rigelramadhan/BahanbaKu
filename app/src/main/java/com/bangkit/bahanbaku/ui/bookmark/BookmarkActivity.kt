package com.bangkit.bahanbaku.ui.bookmark

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.bahanbaku.databinding.ActivityBookmarkBinding

class BookmarkActivity : AppCompatActivity() {

    private val binding: ActivityBookmarkBinding by lazy {
        ActivityBookmarkBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}