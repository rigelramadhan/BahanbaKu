package com.bangkit.bahanbaku.ui.bookmark

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.bahanbaku.adapter.BookmarkAdapter
import com.bangkit.bahanbaku.databinding.FragmentBookmarkBinding
import com.bangkit.bahanbaku.ui.login.LoginActivity
import com.bangkit.bahanbaku.utils.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkFragment : Fragment() {

    private val binding: FragmentBookmarkBinding by lazy {
        FragmentBookmarkBinding.inflate(layoutInflater)
    }

    private lateinit var adapter: BookmarkAdapter
    private var token: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: BookmarkViewModel by viewModels()

        getToken(viewModel)
    }

    private fun getToken(viewModel: BookmarkViewModel) {
        viewModel.getToken().observe(requireActivity()) {
            if (it == "null") {
                val intent = Intent(requireContext(), LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            } else {
                token = it
                setupView(viewModel)
            }
        }
    }

    private fun setupView(viewModel: BookmarkViewModel) {
        if (token != null) {
            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    viewModel.deleteBookmarks(token as String, viewHolder.adapterPosition)
                        .observe(requireActivity()) { result ->
                            if (result is Result.Success) {
                                adapter.notifyItemRemoved(viewHolder.adapterPosition)
                            }
                        }
                }
            }).attachToRecyclerView(binding.rvBookmark)

            viewModel.getBookmarks(token as String).observe(requireActivity()) { result ->
                when (result) {
                    is Result.Loading -> {}
                    is Result.Error -> {}
                    is Result.Success -> {
                        val data = result.data
                        adapter = BookmarkAdapter(data)
                        binding.rvBookmark.apply {
                            adapter = this@BookmarkFragment.adapter
                            layoutManager = LinearLayoutManager(requireContext())
                        }
                    }
                }
            }
        }
    }
}