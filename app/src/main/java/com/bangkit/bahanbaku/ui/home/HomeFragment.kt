package com.bangkit.bahanbaku.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.bahanbaku.R
import com.bangkit.bahanbaku.adapter.HomeRecipeAdapter
import com.bangkit.bahanbaku.databinding.FragmentHomeBinding
import com.bangkit.bahanbaku.ui.bookmark.BookmarkViewModel
import com.bangkit.bahanbaku.ui.login.LoginActivity
import com.bangkit.bahanbaku.ui.profile.ProfileActivity
import com.bangkit.bahanbaku.ui.search.SearchActivity
import com.bangkit.bahanbaku.utils.Result
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    private var token: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: HomeViewModel by viewModels()

        Log.d("MAINACTV", "activity called")
        getToken(viewModel)
    }

    private fun getToken(viewModel: HomeViewModel) {
        viewModel.getToken().observe(requireActivity()) {
            Log.d("MAINACTV", "checking token")
            if (it == "null") {
                val intent = Intent(requireContext(), LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            } else {
                token = "Bearer $it"
                setupView()
                setupData(viewModel)
            }
        }
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
        if (token != null) {
            val token = this.token as String
            viewModel.getFeaturedRecipe(token).observe(requireActivity()) { result ->
                when (result) {
                    is Result.Loading -> {
                        binding.progressBar.isVisible = true
                    }

                    is Result.Error -> {
                        val error = result.error
                        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                        binding.progressBar.isVisible = false
                    }

                    is Result.Success -> {
                        binding.progressBar.isVisible = false
                        val data = result.data
                        binding.cardFeatured.tvRecipe.text = data.title
                        binding.cardFeatured.tvRecipeDescription.text = data.description
                        binding.cardFeatured.tvServings.text =
                            getString(R.string.serving).format(data.servings)

                        Glide.with(this)
                            .load(data.image)
                            .into(binding.cardFeatured.imgRecipe)
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
                        val data = result.data.results
                        Glide.with(requireContext())
                            .load(data.picture)
                            .into(binding.imgProfile)
                    }
                }
            }
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}