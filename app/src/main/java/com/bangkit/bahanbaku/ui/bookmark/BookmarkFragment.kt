package com.bangkit.bahanbaku.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.bahanbaku.R
import com.bangkit.bahanbaku.adapter.BookmarkAdapter
import com.bangkit.bahanbaku.databinding.FragmentBookmarkBinding
import com.bangkit.bahanbaku.utils.Result

class BookmarkFragment : Fragment() {

    private val binding: FragmentBookmarkBinding by lazy {
        FragmentBookmarkBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: BookmarkViewModel by viewModels {
            BookmarkViewModel.BookmarkViewModelFactory.getInstance(requireContext())
        }

        setupView(viewModel)
    }

    private fun setupView(viewModel: BookmarkViewModel) {

    }

    companion object {
        fun newInstance() = BookmarkFragment()
    }
}