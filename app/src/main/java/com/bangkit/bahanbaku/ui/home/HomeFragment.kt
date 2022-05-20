package com.bangkit.bahanbaku.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.bahanbaku.adapter.HomeRecipeAdapter
import com.bangkit.bahanbaku.databinding.FragmentHomeBinding
import com.bangkit.bahanbaku.ui.profile.ProfileActivity
import com.bangkit.bahanbaku.ui.search.SearchActivity
import com.bangkit.bahanbaku.utils.Result
import com.bumptech.glide.Glide

class HomeFragment : Fragment() {

    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: HomeViewModel by viewModels {
            HomeViewModel.HomeViewModelFactory.getInstance(requireContext())
        }

        setupView()
        setupData(viewModel)
    }

    private fun setupView() {
        binding.imgProfile.setOnClickListener {
            val intent = Intent(requireContext(), ProfileActivity::class.java)
            startActivity(intent)
        }

        binding.cardSearch.setOnClickListener {
            val intent = Intent(requireContext(), SearchActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupData(viewModel: HomeViewModel) {
        val token = ""

        viewModel.getFeaturedRecipe(token).observe(requireActivity()) { result ->
            when (result) {
                is Result.Loading -> {

                }

                is Result.Error -> {

                }

                is Result.Success -> {
                    val data = result.data
                    binding.tvRecipe.text = data.title
                    binding.tvRecipeDescription.text = data.description
                    binding.tvAuthor.text = data.author

                    Glide.with(this)
                        .load(data.image)
                        .into(binding.imgRecipe)
                }
            }
        }

        viewModel.getRecipes(token).observe(requireActivity()) { result ->
            when (result) {
                is Result.Loading -> {

                }

                is Result.Error -> {

                }

                is Result.Success -> {
                    val data = result.data
                    binding.rvDiscoverRecipes.apply {
                        adapter = HomeRecipeAdapter(data)
                        layoutManager = LinearLayoutManager(
                            requireContext(),
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )
                    }
                }
            }
        }

        viewModel.getProfile(token).observe(requireActivity()) { result ->
            when (result) {
                is Result.Loading -> {

                }

                is Result.Error -> {

                }

                is Result.Success -> {
                    val data = result.data
                    Glide.with(requireContext())
                        .load(data.picture)
                        .into(binding.imgProfile)
                }
            }
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}