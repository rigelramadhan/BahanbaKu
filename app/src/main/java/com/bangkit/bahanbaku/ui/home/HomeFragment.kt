package com.bangkit.bahanbaku.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.bahanbaku.R
import com.bangkit.bahanbaku.adapter.HomeRecipeAdapter
import com.bangkit.bahanbaku.databinding.FragmentHomeBinding
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

        setupView(viewModel)
    }

    private fun setupView(viewModel: HomeViewModel) {
        viewModel.getFeaturedRecipe().observe(requireActivity()) { result ->
            when (result) {
                is Result.Loading -> {

                }

                is Result.Error -> {

                }

                is Result.Success -> {
                    val data = result.data
                    binding.tvFeaturedRecipe.text = data.name
                    binding.tvFeaturedRecipeDescription.text = data.description
                    binding.tvFeaturedAuthor.text = data.author

                    Glide.with(this)
                        .load(data.photoUrl)
                        .into(binding.imgFeaturedRecipe)
                }
            }
        }

        viewModel.getRecipes().observe(requireActivity()) { result ->
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
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}